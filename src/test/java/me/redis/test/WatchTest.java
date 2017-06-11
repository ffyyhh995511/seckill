package me.redis.test;

import java.util.List;

import me.gacl.commons.ConstantCommons;
import me.gacl.service.JedisClientService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

public class WatchTest {
	
	public static void main(String[] args) {
		WatchTest w = new WatchTest();
		w.uidProcessResult("002");
//		JedisClientService j = new JedisClientService();
//		System.out.println(j.set("abc", "123"));
	}
	
	/**
	 * 处理结果
	 * @param uid
	 * @return
	 * 0：抢购失败
	 * 1：抢购成功
	 * 2：以抢购
	 * 3：库存没有了
	 * 4：系统异常
	 */
	public int uidProcessResult(String uid){
		JedisClientService jedisClientService = new JedisClientService();
		int result = 0;
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			jedisPool = jedisClientService.getJedisPool();
			jedis = jedisPool.getResource();
			//同时watch库存的key、是否已经抢过的key，有并发发生变化就check-and-save
			while("OK".equals(jedis.watch(ConstantCommons.ACCOUNT_KEY,ConstantCommons.ALREADY_ROB))){
				//判断是否已经抢过
				String isExist = jedis.hget(ConstantCommons.ALREADY_ROB, uid);
				if(isExist != null){
					result = 2;
					break;
				}
				String account = jedis.get(ConstantCommons.ACCOUNT_KEY);
				int accountNumber = Integer.valueOf(account);
				//库存没有了
				if(accountNumber == 0){
					result = 3;
					break;
				}
				//个人总结、把需要写的操作放在mult事物中
				Transaction tx = jedis.multi();
				int finalAccount = accountNumber -1;
				System.out.println("剩下有多少个："+account);
				tx.set(ConstantCommons.ACCOUNT_KEY, String.valueOf(finalAccount));
				List<Object> execResult = tx.exec();
				if (execResult == null || execResult.isEmpty()) {
					jedis.unwatch();
				} else {
					System.out.println("恭喜您，" + uid + "已经中标");
					//已经抢成功的人放到缓存
					jedisClientService.hset(ConstantCommons.ALREADY_ROB,uid, uid);
					result = 1;
					break;
				}
			
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(jedis != null){
				jedis.close();
			}
			if(jedisPool != null){
				jedisPool.close();
			}
		}
		return result;
	}
}

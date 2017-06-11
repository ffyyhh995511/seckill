package me.gacl.thread;

import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import me.gacl.commons.ConstantCommons;
import me.gacl.domain.Goods;
import me.gacl.service.GoodsService;
import me.gacl.service.JedisClientService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
/**
 * 队列里面的消息具体逻辑处理
 * @author Administrator
 *
 */
public class RobBusinessRunnable implements Runnable{

	protected final Log logger = LogFactory.getLog(getClass());
	
	private String uid;	
	
	private GoodsService goodsService;
	
	private JedisClientService jedisClientService;
	
	
	public RobBusinessRunnable(){
		
	}
	
	public RobBusinessRunnable(String uid,JedisClientService jedisClientService,GoodsService goodsService){
		this.uid = uid;
		this.jedisClientService = jedisClientService;
		this.goodsService = goodsService;
	}

	public void run() {
		int result = uidProcessResult(uid);
		//数据库记录
		if(result == 1){
			Goods goods = new Goods();
			goods.setUid(uid);
			goods.setId(UUID.randomUUID().toString().replace("-", ""));
			goods.setName("iphone6");
			goodsService.insert(goods);
		}
		
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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
					logger.info("不好意思，你已经抢过了。。。。。。。。。。。");
					break;
				}
				String account = jedis.get(ConstantCommons.ACCOUNT_KEY);
				int accountNumber = Integer.valueOf(account);
				//库存没有了
				if(accountNumber == 0){
					result = 3;
					logger.info("我擦，库存没有了");
					break;
				}
				//个人总结、把需要写的操作放在mult事物中
				Transaction tx = jedis.multi();
				int finalAccount = accountNumber -1;
				logger.info("剩下有多少个："+account);
				tx.set(ConstantCommons.ACCOUNT_KEY, String.valueOf(finalAccount));
				List<Object> execResult = tx.exec();
				if (execResult == null || execResult.isEmpty()) {
					// EXEC 命令或 DISCARD 命令先被执行了的话，那么就不需要再执行 UNWATCH了。
					//jedis.unwatch();
				} else {
					logger.info("恭喜您，" + uid + "已经中标");
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

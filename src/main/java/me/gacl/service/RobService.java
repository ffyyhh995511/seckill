package me.gacl.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import me.gacl.commons.ConstantCommons;
import me.gacl.thread.RpopDataThread;
@Service
public class RobService {
	
	
	@Resource
	private JedisClientService jedisClientService;
	
	@Resource
	private GoodsService goodsService;
	
	
	/**
	 * 把请求放入队列
	 * @param uid
	 * @return
	 */
	public long lpushMyRob(String uid){
		if(StringUtils.isNoneBlank(uid)){
			return jedisClientService.lpush(ConstantCommons.ROB_KEY, uid);
		}
		return 0;
	}
	
	
	@PostConstruct
	public void init(){
		RpopDataThread rpopDataThread = new RpopDataThread(goodsService);
		rpopDataThread.start();
	}
	
//	@Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次  
//    public void myTest(){  
//          System.out.println("进入测试");  
//    }
}

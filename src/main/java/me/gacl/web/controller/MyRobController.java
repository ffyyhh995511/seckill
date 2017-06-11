package me.gacl.web.controller;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.gacl.commons.ConstantCommons;
import me.gacl.domain.Goods;
import me.gacl.service.JedisClientService;
import me.gacl.service.RobService;
/**
 * 抢购controller
 * @author Administrator
 *
 */
@Controller
public class MyRobController extends BaseController{
	
	@Resource
	private RobService myRobService;
	
	@Resource
	private JedisClientService jedisClientService;
	
	/**
	 * 入口
	 * @param uid 用户id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/myRob",method=RequestMethod.GET)
	public Object myRob(){
		//随机生产一个用户id
		int uid = new Random().nextInt(10000);
		return myRobService.lpushMyRob(String.valueOf(uid));			
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public Object test(String uid){
		
		
		return responseSuccess(null, jedisClientService.get(ConstantCommons.ACCOUNT_KEY));
		
	}
}

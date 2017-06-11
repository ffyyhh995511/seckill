package me.gacl.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import me.gacl.commons.ConstantCommons;
import me.gacl.dao.GoodsMapper;
import me.gacl.domain.Goods;
import me.gacl.thread.RobBusinessRunnable;
import me.gacl.thread.RobThreadPool;

@Service
public class GoodsService {
	
	@Resource
	private GoodsMapper goodsMapper;
	
	@Resource
	private JedisClientService jedisClientService;
	
	@Resource
	private GoodsService goodsService;
	
	public int deleteByPrimaryKey(String id){
		return goodsMapper.deleteByPrimaryKey(id);
	}

	public int insert(Goods record){
    	return goodsMapper.insert(record);
    }

	public int insertSelective(Goods record){
    	return goodsMapper.insertSelective(record);
    }

	public Goods selectByPrimaryKey(String id){
    	return goodsMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(Goods record){
    	return goodsMapper.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Goods record){
    	return goodsMapper.updateByPrimaryKey(record);
    }
	
	/**
	 * 监听队列消息
	 */
	public void MonitorQuequData(){
		String uid = jedisClientService.rpop(ConstantCommons.ROB_KEY);
		//用户处理结果判断
		if(StringUtils.isNotBlank(uid)){
			RobBusinessRunnable RobBusinessRunnable = new RobBusinessRunnable(uid, jedisClientService, goodsService);
			RobThreadPool.execute(RobBusinessRunnable);
		}
	}
}

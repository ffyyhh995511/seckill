package me.gacl.thread;

import me.gacl.service.GoodsService;

/**
 * 开一个线程监听队列
 * @author Administrator
 *
 */
public class RpopDataThread extends Thread{
	
	
	private GoodsService goodsService;
	
	public RpopDataThread(){
		
	}
	
	public RpopDataThread(GoodsService goodsService){
		this.goodsService = goodsService;
	}
	
	@Override
	public void run() {
		while(true){
			goodsService.MonitorQuequData();
		}
	}
	
}

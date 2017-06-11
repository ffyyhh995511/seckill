package me.reids.thread.test;

import java.util.UUID;

import me.gacl.service.JedisClientService;

public class Thread1 extends Thread{
	public JedisClientService jedisClient;
	
	public Thread1(JedisClientService jedisClient){
		this.jedisClient = jedisClient;
	}
	
	public void run() {
		for(int i=0;i<1000000;i++){
			jedisClient.set("setkey"+i, UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+"value"+i);
			System.out.println("setkey");
		}
	}
	
}

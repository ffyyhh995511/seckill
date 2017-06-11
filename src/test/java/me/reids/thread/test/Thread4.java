package me.reids.thread.test;

import java.util.UUID;

import me.gacl.service.JedisClientService;

public class Thread4 extends Thread{
	public JedisClientService jedisClient;
	
	public Thread4(JedisClientService jedisClient){
		this.jedisClient = jedisClient;
	}
	
	public void run() {
		for(int i=0;i<1000000;i++){
			jedisClient.sadd("saddkey"+i, UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+"member"+i);
			System.out.println("saddkey");
		}
	}
	
}

package me.reids.thread.test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.gacl.service.JedisClientService;

public class Main {
	public static void main(String[] args) {
		JedisClientService jedisClient = new JedisClientService();
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Thread1 r1 = new Thread1(jedisClient);
		Thread2 r2 = new Thread2(jedisClient);
		Thread3 r3 = new Thread3(jedisClient);
		Thread4 r4 = new Thread4(jedisClient);
		Thread5 r5 = new Thread5(jedisClient);
		executor.execute(r1);
		executor.execute(r2);
		executor.execute(r3);
		executor.execute(r4);
		executor.execute(r5);
		
	}
}

package me.redis.test;

public class Other {
	static{
		System.out.println("static2");
	}
	
	static{
		System.out.println("static");
	}
	
	public static void main(String[] args) {
		System.out.println("main");
	}
}

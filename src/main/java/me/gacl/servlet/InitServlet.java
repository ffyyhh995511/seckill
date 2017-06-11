package me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		super.init();
		
		while(true){
			System.out.println("init InitServlet........................");
		}
	}
	
}

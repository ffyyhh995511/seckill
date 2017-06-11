package me.gacl.web.controller;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class PropertyTestController {
	private static final Logger logger = Logger.getLogger(PropertyTestController.class);  
	@ResponseBody
	@RequestMapping(value="/property",method=RequestMethod.GET)
	public Object property(){
		Properties properties = null;
		try {
			properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("redis.properties"));
		} catch (IOException e) {			
			logger.error(e);
		}
		return properties.getProperty("master_ip");
	}
}

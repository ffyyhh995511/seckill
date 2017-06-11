package me.gacl.web.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 日志demo
 * @author Administrator
 *
 */
@Controller
public class Log4JTestController {
	private static final Logger logger = Logger.getLogger(Log4JTestController.class);  
	@ResponseBody
	@RequestMapping(value="/log4j",method=RequestMethod.GET)
	public Object log4j(){
		logger.info("info.....");
		logger.debug("debugger...");
		return new Date();
	}
}

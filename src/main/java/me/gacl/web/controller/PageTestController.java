package me.gacl.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.gacl.commons.OpenPage;
import me.gacl.domain.Goods;
import me.gacl.service.PageTestServcie;
import me.gacl.vo.StudentTestVo;
/**
 * 分页测试demo
 * @author Administrator
 *
 */
@Controller
public class PageTestController extends BaseController{
	
	@Resource
	private PageTestServcie pageTestServcie;
	
	/**
	 * 简单的单表分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/toPage",method=RequestMethod.GET)
	public Object toPage(Integer pageNum,Integer pageSize){
		OpenPage page = pageTestServcie.selectTest(pageNum,pageSize);
		return responseSuccess(null, page);
	}
	
	/**
	 * 多表查询分页
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/toMPage",method=RequestMethod.GET)
	public Object toMPage(Integer pageNum,Integer pageSize){
		OpenPage page = pageTestServcie.selectWithTeacher(pageNum,pageSize);
		return responseSuccess(null, page);
	}

}

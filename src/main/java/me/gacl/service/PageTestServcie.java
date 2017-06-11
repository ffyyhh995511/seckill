package me.gacl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import me.gacl.commons.OpenPage;
import me.gacl.dao.GoodsMapper;
import me.gacl.dao.StudentTestMapper;
import me.gacl.dao.TeacherTestMapper;
import me.gacl.domain.Goods;
import me.gacl.domain.StudentTest;
import me.gacl.domain.TeacherTest;
import me.gacl.util.UUIDUtil;
import me.gacl.vo.StudentTestVo;

@Service
public class PageTestServcie {
	
	@Resource
	private GoodsMapper goodsMapper;
	
	@Resource
	private StudentTestMapper studentTestMapper;
	
	@Resource
	private TeacherTestMapper teacherTestMapper;
	
	
	/**
	 * 简单的单表分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OpenPage selectTest(Integer pageNum,Integer pageSize){
		//获取第1页，10条内容，默认查询总数count
	    PageHelper.startPage(pageNum, pageSize);

	    //紧跟着的第一个select方法会被分页
	    List<Goods> list = goodsMapper.selectTest();
	    Page p = ((Page) list);
	    
	    return OpenPage.buildPage(p);
	}
	
	
	/**
	 * 简单的多表分页+排序
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public OpenPage selectWithTeacher(Integer pageNum,Integer pageSize){
		//获取第1页，10条内容，默认查询总数count
	    PageHelper.startPage(pageNum, pageSize);

	    //紧跟着的第一个select方法会被分页
	    List<StudentTestVo> list = studentTestMapper.selectWithTeacher();
	    Page p = ((Page) list);
	    
	    return OpenPage.buildPage(p);
	}
}

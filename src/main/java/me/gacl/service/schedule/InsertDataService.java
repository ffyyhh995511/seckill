package me.gacl.service.schedule;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import me.gacl.dao.StudentTestMapper;
import me.gacl.dao.TeacherTestMapper;
import me.gacl.domain.StudentTest;
import me.gacl.domain.TeacherTest;
import me.gacl.util.RandomCodeUtil;
import me.gacl.util.UUIDUtil;

/**
 * 定时插入数据库，用于数据库的性能分析
 * @author Administrator
 *
 */
@Service
public class InsertDataService {
	
	@Resource
	private StudentTestMapper studentTestMapper;
	
	@Resource
	private TeacherTestMapper teacherTestMapper;
	
/*	@Scheduled(cron = "0 0 10 * * *")
	public void insertStudent(){
		System.out.println("单表插入300W数据开始。。。。");
		//单表插入300W数据
		for(int i=0;i<3000000;i++){
			String codes  = RandomCodeUtil.getUniqueCode();
			String codet  = RandomCodeUtil.getUniqueCode();
			String uuids = UUIDUtil.getUUID();
			String uuidt = UUIDUtil.getUUID();
			StudentTest s = new StudentTest();
			s.setId(uuids);
			s.setTeacherid(uuidt);
			s.setName(codes);
			s.setPrade(100);
			//保存
			studentTestMapper.insert(s);
			
			TeacherTest t = new TeacherTest();
			t.setId(uuidt);
			t.setName(codet);
			//保存
			teacherTestMapper.insert(t);
		}
	}*/
	
}

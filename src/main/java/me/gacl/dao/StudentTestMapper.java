package me.gacl.dao;

import java.util.List;

import me.gacl.domain.StudentTest;
import me.gacl.vo.StudentTestVo;

public interface StudentTestMapper {
    int deleteByPrimaryKey(String id);

    int insert(StudentTest record);

    int insertSelective(StudentTest record);

    StudentTest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StudentTest record);

    int updateByPrimaryKey(StudentTest record);
    
    List<StudentTestVo> selectWithTeacher();
}
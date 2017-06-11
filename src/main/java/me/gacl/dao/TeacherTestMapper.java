package me.gacl.dao;

import me.gacl.domain.TeacherTest;

public interface TeacherTestMapper {
    int deleteByPrimaryKey(String id);

    int insert(TeacherTest record);

    int insertSelective(TeacherTest record);

    TeacherTest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TeacherTest record);

    int updateByPrimaryKey(TeacherTest record);
}
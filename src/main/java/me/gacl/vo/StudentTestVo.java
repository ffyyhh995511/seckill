package me.gacl.vo;

import me.gacl.domain.StudentTest;

public class StudentTestVo extends StudentTest {
	private String studentName;
	
	private String teacherName;
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}

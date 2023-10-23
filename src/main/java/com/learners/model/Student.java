package com.learners.model;

public class Student {
	
	private int studId;
	private String studName;
	private String studPwd;
	private int classId;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int studId, String studName, String studPwd, int classId) {
		super();
		this.studId = studId;
		this.studName = studName;
		this.studPwd = studPwd;
		this.classId = classId;
	}

	public int getStudId() {
		return studId;
	}

	public void setStudId(int studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}
	

	public String getStudPwd() {
		return studPwd;
	}

	public void setStudPwd(String studPwd) {
		this.studPwd = studPwd;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "Student [studId=" + studId + ", studName=" + studName + ", studPwd=" + studPwd + ", classId=" + classId
				+ "]";
	}
	
	
	
}

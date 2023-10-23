package com.learners.model;

public class Teacher {
	
	private int tId;
	private String tName;
	private String pwd;
	private int classId;
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(int tId, String tName, String pwd, int classId) {
		super();
		this.tId = tId;
		this.tName = tName;
		this.pwd = pwd;
		this.classId = classId;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "Teacher [tId=" + tId + ", tName=" + tName + ", pwd=" + pwd + ", classId=" + classId + "]";
	}
	
}

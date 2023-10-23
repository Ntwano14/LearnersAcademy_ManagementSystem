package com.learners.model;

public class Class {
	
	private int classId;
	private String className;
	
	public Class() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Class(int classId, String className) {
		super();
		this.classId = classId;
		this.className = className;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "Class [classId=" + classId + ", className=" + className + "]";
	}

}

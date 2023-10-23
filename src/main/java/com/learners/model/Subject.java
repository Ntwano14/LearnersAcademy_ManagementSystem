package com.learners.model;

public class Subject {
	
	private int subjId;
	private String subjName;
	
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subject(int subjId, String subjName) {
		super();
		this.subjId = subjId;
		this.subjName = subjName;
	}
	public int getSubjId() {
		return subjId;
	}
	public void setSubjId(int subjId) {
		this.subjId = subjId;
	}
	public String getSubjName() {
		return subjName;
	}
	public void setSubjName(String subjName) {
		this.subjName = subjName;
	}
	@Override
	public String toString() {
		return "Subject [subjId=" + subjId + ", subjName=" + subjName + "]";
	}
	
}

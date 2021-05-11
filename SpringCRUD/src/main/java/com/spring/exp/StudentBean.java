package com.spring.exp;

import java.sql.Date;

public class StudentBean {

	int studentId;
	String name;
	String surName;
	float percentage;
	Date dob;
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public StudentBean() {
		super();
	}
	public StudentBean(int studentId, String name, String surName) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.surName = surName;
		
	}
	
	
	
}

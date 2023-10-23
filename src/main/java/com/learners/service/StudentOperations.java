package com.learners.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.learners.dbconnection.DBConnection;
import com.learners.model.Student;

public class StudentOperations {
	
	private Connection conObj = null;

	public StudentOperations() {
		conObj = DBConnection.getConnection();
	}

	public String AddNewStudent(Student std)
	{
		String addStud ="Error";

		try
		{
			String inscmd = "Insert into students (student_name,class_id, student_password) value (?,?,?)";

			PreparedStatement ps = conObj.prepareStatement(inscmd);

			ps.setString(1, std.getStudName());
			ps.setInt(2, std.getClassId());
			ps.setString(3, std.getStudPwd());

			int r =ps.executeUpdate();

			if(r>=1)
			{
				addStud="Success";
			}
		}
		catch(Exception ex)
		{
			addStud=ex.getMessage();
			ex.printStackTrace();
		}
		return addStud;
	}

	public List<Student> showAll()
	{
		List<Student> sall = new ArrayList();

		Student std=null;

		try {
			PreparedStatement ps= conObj.prepareStatement("select * from students");
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				std= new Student();
				
				std.setStudId(rs.getInt("student_id"));
				std.setStudName(rs.getString("student_name"));
				std.setClassId(rs.getInt("class_id"));
				std.setStudPwd(rs.getString("student_password"));
				sall.add(std);
			}

		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return sall;
	}
	
	public Student SearchStudent(int studId)
	{
		Student std= null;

		try {

			PreparedStatement ps= conObj.prepareStatement("select * from students where student_id=?");

			ps.setInt(1, studId);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				std= new Student();
				std.setStudId(rs.getInt("student_id"));
				std.setStudName(rs.getString("student_name"));
				std.setClassId(rs.getInt("class_id"));
				std.setStudPwd(rs.getString("student_password"));
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return std;
	}


	public String DeleteStudent(int studId)
	{
		String res="Error";
		try {

			String delcmd= "Delete from students where student_id=?";

			PreparedStatement ps= conObj.prepareStatement(delcmd);
			ps.setInt(1, studId);

			int r= ps.executeUpdate();

			if(r>=1)
			{
				res="Success";
			}

		} 
		catch (Exception ex) {
			res=ex.getMessage();
			ex.printStackTrace();
		}

		return res;

	}

	public String UpdateStudentName(Student std)
	{
		String res="Error";

		try {
			String uptcmd= "update students set student_name=? where student_id=?";

			PreparedStatement ps = conObj.prepareStatement(uptcmd);
			ps.setString(1, std.getStudName());
			ps.setInt(2, std.getStudId());
			ps.setString(3, std.getStudPwd());
			
			int r= ps.executeUpdate();
			if(r>=1)
			{
				res="Success";

			}

		}
		catch (Exception ex) {
			res=ex.getMessage();
			ex.printStackTrace();
		}

		return res;

	}


	public Student CheckStudent(String uname, String pwd)
	{
		Student std= null;

		try {

			PreparedStatement ps= conObj.prepareStatement("select * from students where student_name=? and student_password=?");

			ps.setString(1, uname);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				std= new Student();
				std.setStudId(rs.getInt("student_id"));
				std.setStudName(rs.getString("student_name"));
				std.setClassId(rs.getInt("class_id"));
				std.setStudPwd(rs.getString("student_password"));
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return std;
	}

}

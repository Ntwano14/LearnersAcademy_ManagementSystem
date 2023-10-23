package com.learners.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.learners.dbconnection.DBConnection;
import com.learners.model.Teacher;

public class TeacherOperations {
	
	private Connection conObj = null;

	public TeacherOperations() {
		conObj = DBConnection.getConnection();
	}

	public String AddNewTeacher(Teacher teach)
	{
		String addTeacher ="Error";

		try
		{
			String inscmd = "Insert into teachers (teacher_name, teacher_password, class_id) value (?,?,?)";

			PreparedStatement ps = conObj.prepareStatement(inscmd);

			ps.setString(1, teach.gettName());
			ps.setString(2, teach.getPwd());
			ps.setInt(3, teach.getClassId());

			int r =ps.executeUpdate();

			if(r>=1)
			{
				addTeacher="Success";
			}
		}
		catch(Exception ex)
		{
			addTeacher=ex.getMessage();
			ex.printStackTrace();
		}
		return addTeacher;
	}

	public List<Teacher> showAll()
	{
		List<Teacher> sall = new ArrayList();

		Teacher teach=null;

		try {
			PreparedStatement ps= conObj.prepareStatement("select * from teachers");
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				teach= new Teacher();
				
				teach.settId(rs.getInt("teacher_id"));
				teach.settName(rs.getString("teacher_name"));
				teach.setPwd(rs.getString("teacher_password"));
				teach.setClassId(rs.getInt("class_id"));

				sall.add(teach);
			}

		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return sall;
	}
	
	public Teacher SearchTeacher(int teacherId)
	{
		Teacher teach= null;

		try {

			PreparedStatement ps= conObj.prepareStatement("select * from teachers where teacher_id=?");

			ps.setInt(1, teacherId);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				teach= new Teacher();
				teach.settId(rs.getInt("teacher_id"));
				teach.settName(rs.getString("teacher_name"));
				teach.setPwd(rs.getString("teacher_password"));
				teach.setClassId(rs.getInt("class_id"));
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return teach;
	}


	public String DeleteTeacher(int teacherId)
	{
		String res="Error";
		try {

			String delcmd= "Delete from teachers where teacher_id=?";

			PreparedStatement ps= conObj.prepareStatement(delcmd);
			ps.setInt(1, teacherId);

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

	public String UpdateTeacher(Teacher teach)
	{
		String res="Error";

		try {
			String uptcmd= "update teachers set teacher_name=? where teacher_password=?";

			PreparedStatement ps = conObj.prepareStatement(uptcmd);
			ps.setString(1, teach.gettName());
			ps.setString(2, teach.getPwd());
			ps.setInt(3, teach.getClassId());
			
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


	public Teacher CheckTeacher(String id, String name)
	{
		Teacher teach= null;

		try {

			PreparedStatement ps= conObj.prepareStatement("select * from teachers where teacher_id=? and teacher_name=?");

			ps.setString(1, id);
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				teach= new Teacher();
				teach.settId(rs.getInt("teacher_id"));
				teach.settName(rs.getString("teacher_name"));
				teach.setPwd(rs.getString("teacher_password"));
				teach.setClassId(rs.getInt("class_id"));
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return teach;
	}

}

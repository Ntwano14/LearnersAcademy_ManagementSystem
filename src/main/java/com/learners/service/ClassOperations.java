package com.learners.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.learners.dbconnection.DBConnection;
import com.learners.model.Class;

public class ClassOperations {
	
	private Connection conObj = null;

	public ClassOperations() {
		conObj = DBConnection.getConnection();
	}

	public String AddNewClass(Class clss)
	{
		String addClas ="Error";

		try
		{
			String insertcmd="Insert into classes(class_name) values(?)";

			PreparedStatement ps = conObj.prepareStatement(insertcmd);

			ps.setString(1, clss.getClassName());

			int r =ps.executeUpdate();

			if(r>=1)
			{
				addClas="Success";
			}
		}
		catch(Exception ex)
		{
			addClas=ex.getMessage();
			ex.printStackTrace();
		}
		return addClas;
	}

	public List<Class> showAll()
	{
		List<Class> allClasses = new ArrayList();

		Class clss=null;

		try {
			PreparedStatement ps= conObj.prepareStatement("select * from classes");
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				clss= new Class();
				
				clss.setClassId(rs.getInt("class_id"));
				clss.setClassName(rs.getString("class_name"));

				allClasses.add(clss);

			}

		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}


		return allClasses;
	}


	public Class SearchClass(int classId)
	{
		Class clss= null;

		try {

			PreparedStatement ps= conObj.prepareStatement("select * from classes where class_id=?");

			ps.setInt(1, classId);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				clss= new Class();
				clss.setClassId(rs.getInt("class_id"));
				clss.setClassName(rs.getString("class_name"));
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return clss;
	}


	public String DeleteClass(int classId)
	{
		String result ="Error";
		try {

			String delcmd= "Delete from classes where class_id=?";

			PreparedStatement ps= conObj.prepareStatement(delcmd);
			ps.setInt(1, classId);

			int r= ps.executeUpdate();

			if(r>=1)
			{
				result="Success";
			}

		} 
		catch (Exception ex) {
			result=ex.getMessage();
			ex.printStackTrace();
		}

		return result;

	}

	public String UpdateStudentName(Class clss)
	{
		String res="Error";

		try {
			String uptcmd= "update classes set class_name=? where class_id=?";

			PreparedStatement ps = conObj.prepareStatement(uptcmd);
			ps.setString(1, clss.getClassName());
			ps.setInt(2, clss.getClassId());

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


	public Class CheckClass(String className, int classId)
	{
		Class clss= null;

		try {

			PreparedStatement ps= conObj.prepareStatement("select * from classes where class_name=? and class_id=?");

			ps.setString(1, className);
			ps.setInt(2, classId);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				clss= new Class();
				clss.setClassId(rs.getInt("class_id"));
				clss.setClassName(rs.getString("class_name"));
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return clss;
	}

}

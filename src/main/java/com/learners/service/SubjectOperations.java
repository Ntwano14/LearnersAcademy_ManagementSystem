package com.learners.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.learners.dbconnection.DBConnection;
import com.learners.model.Subject;

public class SubjectOperations {

	private Connection conObj = null;

	public SubjectOperations() {
		conObj = DBConnection.getConnection();
	}

	public String AddNewSubject(Subject subj)
	{
		String addSubj ="Error";

		try
		{
			String insertcmd="Insert into subjects (subject_name) values(?)";

			PreparedStatement ps = conObj.prepareStatement(insertcmd);

			ps.setString(1, subj.getSubjName());

			int r =ps.executeUpdate();

			if(r>=1)
			{
				addSubj="Success";
			}
		}
		catch(Exception ex)
		{
			addSubj=ex.getMessage();
			ex.printStackTrace();
		}
		return addSubj;
	}

	public List<Subject> showAll()
	{
		List<Subject> allSubjects = new ArrayList();

		Subject subj = null;

		try {
			PreparedStatement ps= conObj.prepareStatement("select * from subjects");
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				subj= new Subject();

				subj.setSubjId(rs.getInt("subject_id"));
				subj.setSubjName(rs.getString("subject_name"));

				allSubjects.add(subj);

			}

		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}


		return allSubjects;
	}


	public Subject SearchSubject(int subjId)
	{
		Subject subj= null;

		try {

			PreparedStatement ps= conObj.prepareStatement("select * from subjects where subj_id=?");

			ps.setInt(1, subjId);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				subj= new Subject();
				subj.setSubjId(rs.getInt("subject_id"));
				subj.setSubjName(rs.getString("subject_name"));
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}

		return subj;
	}


	public String DeleteSubject(int subjId)
	{
		String result ="Error";
		try {

			String delcmd= "Delete from subjects where subject_id=?";

			PreparedStatement ps= conObj.prepareStatement(delcmd);
			ps.setInt(1, subjId);

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

	public String UpdateSubjectName(Subject subj)
	{
		String result="Error";

		try {
			String uptcmd= "update subjects set subject_name=? where subject_id=?";

			PreparedStatement ps = conObj.prepareStatement(uptcmd);
			ps.setString(1, subj.getSubjName());
			ps.setInt(2, subj.getSubjId());

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


	public Subject CheckSubject(String subjName, int subjId)
	{
		Subject subj= null;

		try {

			PreparedStatement ps= conObj.prepareStatement("select * from subjects where subject_name=? and subject_id=?");

			ps.setString(1, subjName);
			ps.setInt(2, subjId);
			ResultSet rs = ps.executeQuery();

			if(rs.next())
			{
				subj= new Subject();
				subj.setSubjId(rs.getInt("subject_id"));
				subj.setSubjName(rs.getString("subject_name"));
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}	

		return subj;
	}

}

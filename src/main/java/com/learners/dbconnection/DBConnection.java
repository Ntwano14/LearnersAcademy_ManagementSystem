package com.learners.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection()
	{
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/learnersacademysystemdb";
			String username = "root";
			String password = "NTWAno@16";

			con = DriverManager.getConnection(url, username, password);

		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return con;
	}
}

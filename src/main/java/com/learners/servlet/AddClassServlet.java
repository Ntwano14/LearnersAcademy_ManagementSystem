package com.learners.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learners.dbconnection.DBConnection;
import com.learners.service.ClassOperations;
import com.learners.model.Class;
import com.learners.service.ClassOperations;

/**
 * Servlet implementation class AddClassServlet
 */
@WebServlet("/AddClassServlet")
public class AddClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cName = request.getParameter("className");

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		ClassOperations co = new ClassOperations();
		Class clss = new Class();
		
		try
		{
			clss.setClassName(cName);
			
			String addClass = co.AddNewClass(clss); 
			
			if(addClass.equals("Success"))
			{
				out.print("Class added successfully!..");
		
				response.sendRedirect("ShowAllClasses");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

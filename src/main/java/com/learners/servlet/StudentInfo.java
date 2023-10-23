package com.learners.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.learners.model.Class;
import com.learners.model.Student;
import com.learners.service.ClassOperations;
import com.learners.service.StudentOperations;

/**
 * Servlet implementation class StudentInfo
 */
@WebServlet("/StudentInfo")
public class StudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		int sId = (int) session.getAttribute("student_id");
		
		StudentOperations so = new StudentOperations();
		Student std = so.SearchStudent(sId);
		
		out.print("<a href='StudentInfo'style='color:red;'>Home</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
				+ "<a href = '#'style='color:red;'>Change Password</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
				+ " <a href='Login.html' style='color:red;'>Logout </a>");
		out.print("<hr>");
		out.print("<h2>Welcome to: "+std.getStudName()+ " Portal"+"</h2>");
		out.print("<hr>");
		out.print("<table width='100%' border='1'>");
		
		out.print("<tr><th>Student ID</th><th>Student Name</th><th>Class ID</th><th>Student Password</th></tr>");
		
		out.print("<tr>");
		out.print("<td>"+std.getStudId()+"</td>");
		out.print("<td>"+std.getStudName()+"</td>");
		out.print("<td>"+std.getClassId()+"</td>");
        out.print("<td>" + std.getStudPwd() + "</td>");
        
		out.print("</tr>");
		
	out.print("</table>");

	}

}

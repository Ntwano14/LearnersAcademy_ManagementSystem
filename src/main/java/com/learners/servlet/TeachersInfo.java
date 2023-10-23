package com.learners.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.learners.model.Teacher;
import com.learners.service.TeacherOperations;

/**
 * Servlet implementation class TeachersInfo
 */
@WebServlet("/TeachersInfo")
public class TeachersInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeachersInfo() {
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
		
		int tId = (int) session.getAttribute("teacher_id");
		
		TeacherOperations so = new TeacherOperations();
		Teacher teach = so.SearchTeacher(tId);
		
		out.print("<a href='TeacherInfo'style='color:red;'>Home</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
				+ "<a href = '#'style='color:red;'>Change Password</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
				+ " <a href='Login.html' style='color:red;'>Logout </a>");
		out.print("<hr>");
		out.print("<h2>Welcome to: "+teach.gettName()+ " Portal"+"</h2>");
		out.print("<hr>");
		out.print("<table width='100%' border='1'>");
		
		out.print("<tr><th>Teacher ID</th><th>Teacher Name</th><th>Class ID</th><th>Teacher Password</th></tr>");
		
		out.print("<tr>");
		out.print("<td>"+teach.gettId()+"</td>");
		out.print("<td>"+teach.gettName()+"</td>");
		out.print("<td>"+teach.getClassId()+"</td>");
        out.print("<td>" + teach.getPwd() + "</td>");
        
		out.print("</tr>");
		
	out.print("</table>");

	}

}

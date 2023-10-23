package com.learners.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learners.service.ClassOperations;
import com.learners.model.Class;
import com.learners.service.ClassOperations;

/**
 * Servlet implementation class ShowAllClasses
 */
@WebServlet("/ShowAllClasses")
public class ShowAllClasses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllClasses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		ClassOperations co = new ClassOperations();
		
		try
		{
			List<Class> allClasses = co.showAll();
			
			out.print("<a href='addClass.html'style='color:red;'>New Class</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
					+ "<a href = 'ShowAllClasses'style='color:red;'>View All Classes</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
					+ " <a href='Login.html' style='color:red;'>Logout </a>");
			
			out.print("<br>");
			out.print("<hr>");
			out.print("<table width='100%' border='1'>");
			
			out.print("<tr><th>Class ID</th><th>Class Name</th></tr>");
			
			for(Class clss : allClasses)
			{
				out.print("<tr>");
				out.print("<td>"+clss.getClassId()+"</td>");
				out.print("<td>"+clss.getClassName()+"</td>");
				out.print("<td><a href='DeleteClass?rno="+clss.getClassId()+"'>Delete</a></td>");
				out.print("</tr>");
			}
			
			out.print("</table>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
package com.learners.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learners.model.Class;
import com.learners.model.Subject;
import com.learners.service.ClassOperations;
import com.learners.service.SubjectOperations;

/**
 * Servlet implementation class ShowAllSubjects
 */
@WebServlet("/ShowAllSubjects")
public class ShowAllSubjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllSubjects() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		SubjectOperations so = new SubjectOperations();
		
		try
		{
			List<Subject> allsubjects = so.showAll();
			
			out.print("<a href='addSubject.html'style='color:red;'>New Subject</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
					+ "<a href = 'ShowAllSubjects'style='color:red;'>View All Subjects</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
					+ " <a href='Login.html' style='color:red;'>Logout </a>");
			
			out.print("<br>");
			out.print("<hr>");
			out.print("<table width='100%' border='1'>");
			
			out.print("<tr><th>Subject ID</th><th>Subject Name</th></tr>");
			
			for(Subject subj : allsubjects)
			{
				out.print("<tr>");
				out.print("<td>"+subj.getSubjId()+"</td>");
				out.print("<td>"+subj.getSubjName()+"</td>");
				out.print("<td><a href='DeleteSubject?rno="+subj.getSubjId()+"'>Delete</a></td>");
				out.print("</tr>");
			}
			
			out.print("</table>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
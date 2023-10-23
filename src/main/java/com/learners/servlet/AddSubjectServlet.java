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
import com.learners.model.Class;
import com.learners.model.Subject;
import com.learners.service.ClassOperations;
import com.learners.service.SubjectOperations;

/**
 * Servlet implementation class AddSubjectServlet
 */
@WebServlet("/AddSubjectServlet")
public class AddSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sName = request.getParameter("subjName");

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		SubjectOperations so = new SubjectOperations();
		Subject subj = new Subject();
		
		try
		{
			subj.setSubjName(sName);
			
			String addSubj = so.AddNewSubject(subj); 
			
			if(addSubj.equals("Success"))
			{
				out.print("Subject added successfully!..");
		
				response.sendRedirect("ShowAllSubjects");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

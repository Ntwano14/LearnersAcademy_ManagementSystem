package com.learners.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learners.model.Student;
import com.learners.service.StudentOperations;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String sname= request.getParameter("studentName");
		String cId = request.getParameter("classId");
		String sPwd = request.getParameter("studentPwd");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		StudentOperations so = new StudentOperations();
		Student std = new Student();
		
		try
		{
			
			std.setStudName(sname);
			std.setClassId(Integer.parseInt(cId));
			std.setStudPwd(sPwd);
			
			String addStud=so.AddNewStudent(std); 
			
			if(addStud.equals("Success"))
			{
				out.print("Student Added successfully.... ");
		
				response.sendRedirect("ShowAllStudents");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

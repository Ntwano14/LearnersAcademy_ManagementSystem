package com.learners.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learners.model.Teacher;
import com.learners.service.TeacherOperations;

/**
 * Servlet implementation class AddTeacherServlet
 */
@WebServlet("/AddTeacherServlet")
public class AddTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTeacherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tname= request.getParameter("TeacherName");
		String pwd = request.getParameter("tPassword");
		String cId = request.getParameter("classId");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		TeacherOperations so = new TeacherOperations();
		Teacher teach = new Teacher();

		try
		{

			teach.settName(tname);
			teach.setPwd(pwd);
			teach.setClassId(Integer.parseInt(cId));


			String addTeach=so.AddNewTeacher(teach); 

			if(addTeach.equals("Success"))
			{
				out.print("Student Added successfully.... ");

				response.sendRedirect("ShowAllTeachers");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

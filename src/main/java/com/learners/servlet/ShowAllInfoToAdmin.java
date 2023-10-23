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
import com.learners.model.Student;
import com.learners.model.Subject;
import com.learners.model.Teacher;
import com.learners.service.ClassOperations;
import com.learners.service.StudentOperations;
import com.learners.service.SubjectOperations;
import com.learners.service.TeacherOperations;

/**
 * Servlet implementation class ShowAllInfoToAdmin
 */
@WebServlet("/ShowAllInfoToAdmin")
public class ShowAllInfoToAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowAllInfoToAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		StudentOperations studentOperations = new StudentOperations();
		ClassOperations classOperations = new ClassOperations();

		try {
			List<Student> studentList = studentOperations.showAll();

			out.print("<a href='ShowAllInfoToAdmin'style='color:red;'>Students Information</a>&nbsp; &nbsp; &nbsp;<a href='Login.html' style='color:red;'>Logout </a>");

			out.print("<br>");
			out.print("<hr>");
			out.print("<table width='100%' border='1'>");

			out.print("<tr><th>Student ID</th><th>Student Name</th><th>Class Id</th><th>Student Class</th></tr>");

			for (Student student : studentList) {
				out.print("<tr>");
				out.print("<td>" + student.getStudId() + "</td>");
				out.print("<td>" + student.getStudName() + "</td>");
				out.print("<td>" + student.getClassId() + "</td>");

				// Fetch class details based on student classId
				Class studentClass = classOperations.SearchClass(student.getClassId());
				if (studentClass != null) {
					out.print("<td>" + studentClass.getClassName() + "</td>");
				} else {
					out.print("<td>N/A</td>"); // Display N/A if class details not found
				}
				out.print("<td><a href='DeleteStudent?rno=" + student.getStudId() + "'>Delete</a></td>");

				out.print("</tr>");
			}

			out.print("</table>");

		} catch (Exception e) {
			e.printStackTrace();
		}

		//Teacher info
		TeacherOperations to = new TeacherOperations();
		//ClassOperations classOperations = new ClassOperations();

		try {
			List<Teacher> teacherList = to.showAll();

			out.print("<a href='ShowAllInfoToAdmin'style='color:red;'>Teachers Information</a>");

			out.print("<br>");
			out.print("<hr>");
			out.print("<table width='100%' border='1'>");

			out.print("<tr><th>Teacher ID</th><th>Teacher Name</th><th>Class ID</th><th>Teacher Class</th></tr>");

			for (Teacher teacher : teacherList) {
				out.print("<tr>");
				out.print("<td>" + teacher.gettId() + "</td>");
				out.print("<td>" + teacher.gettName() + "</td>");
				out.print("<td>"+teacher.getClassId() + "</td>");

				// Fetch class details based on student classId
				Class teacherClass = classOperations.SearchClass(teacher.getClassId());
				if (teacherClass != null) {
					out.print("<td>" + teacherClass.getClassName() + "</td>");
				} else {
					out.print("<td>N/A</td>"); // Display N/A if class details not found
				}

				out.print("<td><a href='DeleteTeacher?rno=" + teacher.gettId() + "'>Delete</a></td>");

				out.print("</tr>");
			}

			out.print("</table>");

		} catch (Exception e) {
			e.printStackTrace();
		}

		//classes Info
		ClassOperations co = new ClassOperations();

		try
		{
			List<Class> allClasses = co.showAll();

			out.print("<a href='ShowAllInfoToAdmin'style='color:red;'>Classes Information</a>");


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
		
		//Subjects Info
		
SubjectOperations so = new SubjectOperations();
		
		try
		{
			List<Subject> allsubjects = so.showAll();
			
			out.print("<a href='ShowAllInfoToAdmin'style='color:red;'>Subjects Information</a>");
			
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
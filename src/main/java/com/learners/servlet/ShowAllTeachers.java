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
import com.learners.model.Teacher;
import com.learners.service.ClassOperations;
import com.learners.service.StudentOperations;
import com.learners.service.TeacherOperations;

/**
 * Servlet implementation class ShowAllTeachers
 */
@WebServlet("/ShowAllTeachers")
public class ShowAllTeachers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllTeachers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

	    PrintWriter out = response.getWriter();
	    TeacherOperations to = new TeacherOperations();
	    ClassOperations classOperations = new ClassOperations();

	    try {
	        List<Teacher> teacherList = to.showAll();

	        out.print("<a href='addTeacher.html' style='color:red;'>New Teacher</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
	                + "<a href='ShowAllTeachers' style='color:red;'>View All Teachers</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
	                + "<a href='Login.html' style='color:red;'>Logout </a>");

	        out.print("<br>");
	        out.print("<hr>");
	        out.print("<table width='100%' border='1'>");

	        out.print("<tr><th>Teacher ID</th><th>Teacher Name</th><th>Teacher Password</th><th>Class ID</th><th>Teacher Class</th></tr>");

	        for (Teacher teacher : teacherList) {
	            out.print("<tr>");
	            out.print("<td>" + teacher.gettId() + "</td>");
	            out.print("<td>" + teacher.gettName() + "</td>");
	            out.print("<td>" + teacher.getPwd() + "</td>");
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
	}
}

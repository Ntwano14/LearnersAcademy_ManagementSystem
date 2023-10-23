package com.learners.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learners.model.Student;
import com.learners.service.ClassOperations;
import com.learners.service.StudentOperations;
import com.learners.model.Class;

/**
 * Servlet implementation class ShowAllStudents
 */
@WebServlet("/ShowAllStudents")
public class ShowAllStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllStudents() {
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

	        out.print("<a href='addStudent.html' style='color:red;'>New Student</a> &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
	                + "<a href='ShowAllStudents' style='color:red;'>View All Students</a>&nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;"
	                + "<a href='Login.html' style='color:red;'>Logout </a>");

	        out.print("<br>");
	        out.print("<hr>");
	        out.print("<table width='100%' border='1'>");

	        out.print("<tr><th>Student ID</th><th>Student Name</th><th>Class Id</th><th>Student Class</th><th>Student password</th></tr>");

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
	            out.print("<td>" + student.getStudPwd() + "</td>");
	            out.print("<td><a href='DeleteStudent?rno=" + student.getStudId() + "'>Delete</a></td>");

	            out.print("</tr>");
	        }

	        out.print("</table>");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	}
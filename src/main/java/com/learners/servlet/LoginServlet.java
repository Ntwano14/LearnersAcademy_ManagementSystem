package com.learners.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.learners.model.Student;
import com.learners.model.Teacher;
import com.learners.service.StudentOperations;
import com.learners.service.TeacherOperations;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    String uname = request.getParameter("txtUname");
		    String pwd = request.getParameter("txtPwd");

		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();

		    HttpSession session = request.getSession();

		    if (uname.equalsIgnoreCase("Admin") && pwd.equals("admin@123")) 
		    {
		        session.setAttribute("user", "Administrator");
		        response.sendRedirect("ShowAllInfoToAdmin");
		    } 
		    else 
		    {
		        TeacherOperations to = new TeacherOperations();
		        Teacher teach = to.CheckTeacher(uname, pwd);

		        if (teach != null) 
		        {
		            session.setAttribute("teacher_id", teach.gettId());
		            response.sendRedirect("TeachersInfo");
		        } 
		        else 
		        {
		            StudentOperations so = new StudentOperations();
		            Student std = so.CheckStudent(uname, pwd);

		            if (std != null) 
		            {
		                session.setAttribute("student_id", std.getStudId());
		                response.sendRedirect("StudentInfo");
		            } 
		            else 
		            {
		                out.print("<p style='text-align:center'>Error...! Please check username / password</p>");
		                RequestDispatcher rd = request.getRequestDispatcher("Login.html");
		                rd.include(request, response);
		            }
		        }
		    }
		}
}
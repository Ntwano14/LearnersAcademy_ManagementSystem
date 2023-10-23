package com.learners.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learners.dbconnection.DBConnection;

/**
 * Servlet implementation class ClassStudentInfoServlet
 */
@WebServlet("/ClassStudentInfoServlet")
public class ClassStudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassStudentInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	      String action = request.getParameter("action");
	        Connection connection = DBConnection.getConnection();

	        try {
	            if ("assignStudentToClass".equals(action)) {
	                int classId = Integer.parseInt(request.getParameter("classId"));
	                int subjectId = Integer.parseInt(request.getParameter("subjectId"));
	                int studentId = Integer.parseInt(request.getParameter("studentId"));

	                // Assign student to class
	                try (PreparedStatement assignStatement = connection.prepareStatement("INSERT INTO class_students (class_id, subject_id, student_id) VALUES (?, ?, ?)")) {
	                    assignStatement.setInt(1, classId);
	                    assignStatement.setInt(2, subjectId);
	                    assignStatement.setInt(3, studentId);
	                    assignStatement.executeUpdate();
	                }

	                // Retrieve student, class, and subject information
	                String query = "SELECT students.student_name, classes.class_name, subjects.subject_name " +
	                               "FROM class_students " +
	                               "JOIN students ON class_students.student_id = students.student_id " +
	                               "JOIN classes ON class_students.class_id = classes.class_id " +
	                               "JOIN subjects ON class_students.subject_id = subjects.subject_id " +
	                               "WHERE class_students.class_id = ? AND class_students.subject_id = ?";
	                try (PreparedStatement statement = connection.prepareStatement(query)) {
	                    statement.setInt(1, classId);
	                    statement.setInt(2, subjectId);
	        
	                    ResultSet resultSet = statement.executeQuery();

	                    if (resultSet.next()) {
	                        String studentName = resultSet.getString("student_name");
	                        String className = resultSet.getString("class_name");
	                        String subjectName = resultSet.getString("subject_name");

	                        request.setAttribute("studentName", studentName);
	                        request.setAttribute("className", className);
	                        request.setAttribute("subjectName", subjectName);
	                        RequestDispatcher dispatcher = request.getRequestDispatcher("student_info.jsp");
	                        dispatcher.forward(request, response);
	                    } else {
	                        response.getWriter().println("No information found for the provided IDs.");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception according to your needs
	        }
	    }
	}
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
 * Servlet implementation class ClassTeacherInfoServlet
 */
@WebServlet("/ClassTeacherInfoServlet")
public class ClassTeacherInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassTeacherInfoServlet() {
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
            if ("assignTeacherToClass".equals(action)) {
                int classId = Integer.parseInt(request.getParameter("classId"));
                int subjectId = Integer.parseInt(request.getParameter("subjectId"));
                int teacherId = Integer.parseInt(request.getParameter("teacherId"));

                // Assign teacher to class
                try (PreparedStatement assignStatement = connection.prepareStatement("INSERT INTO class_teachers (class_id, subject_id, teacher_id) VALUES (?, ?, ?)")) {
                    assignStatement.setInt(1, classId);
                    assignStatement.setInt(2, subjectId);
                    assignStatement.setInt(3, teacherId);
                    assignStatement.executeUpdate();
                }

                // Retrieve teacher, class, and subject information
                String query = "SELECT teachers.teacher_name, classes.class_name, subjects.subject_name " +
                               "FROM class_teachers " +
                               "JOIN teachers ON class_teachers.teacher_id = teachers.teacher_id " +
                               "JOIN classes ON class_teachers.class_id = classes.class_id " +
                               "JOIN subjects ON class_teachers.subject_id = subjects.subject_id " +
                               "WHERE class_teachers.class_id = ? AND class_teachers.subject_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, classId);
                    statement.setInt(2, subjectId);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        String teacherName = resultSet.getString("teacher_name");
                        String className = resultSet.getString("class_name");
                        String subjectName = resultSet.getString("subject_name");

                        request.setAttribute("teacherName", teacherName);
                        request.setAttribute("className", className);
                        request.setAttribute("subjectName", subjectName);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("teacher_info.jsp");
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

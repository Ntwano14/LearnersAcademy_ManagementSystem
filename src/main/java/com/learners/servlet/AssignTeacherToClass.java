package com.learners.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learners.dbconnection.DBConnection;

/**
 * Servlet implementation class AssignTeacherToClass
 */
@WebServlet("/AssignTeacherToClass")
public class AssignTeacherToClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignTeacherToClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		Connection connection = DBConnection.getConnection();
		try
		{
		if ("assignTeacherToClass".equals(action)) {
            int classId = Integer.parseInt(request.getParameter("classId"));
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            int teacherId = Integer.parseInt(request.getParameter("teacherId"));
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO class_teachers (class_id, subject_id, teacher_id) VALUES (?, ?, ?)")) {
                statement.setInt(1, classId);
                statement.setInt(2, subjectId);
                statement.setInt(3, teacherId);
                statement.executeUpdate();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    response.sendRedirect("confirmation.html");

	}

}

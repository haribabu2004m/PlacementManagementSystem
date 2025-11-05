package com.placement.servlets.student;

import com.placement.dao.DBConnection;
import com.placement.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");

        try (Connection conn = DBConnection.getConnection()) {
            // ✅ fetch student record matching the logged-in user's roll number
            String sql = "SELECT * FROM students WHERE roll_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setRollNumber(rs.getString("roll_number"));
                student.setName(rs.getString("name"));
                student.setYear(rs.getInt("year"));
                student.setDepartment(rs.getString("department"));
                student.setCgpa(rs.getDouble("cgpa"));
                student.setTenthScore(rs.getDouble("tenth_score"));
                student.setTwelfthScore(rs.getDouble("twelfth_score"));
                student.setEmail(rs.getString("email"));
                student.setPhoneNumber(rs.getString("phone_number"));
                student.setWilling(rs.getBoolean("is_willing"));
                student.setPlaced(rs.getBoolean("is_placed"));
                student.setCompany(rs.getString("company"));
                student.setLastUpdated(rs.getTimestamp("last_updated")); // ✅ added

                // Forward to JSP with student object
                request.setAttribute("student", student);
                request.getRequestDispatcher("/student/dashboard.jsp").forward(request, response);
            } else {
                response.getWriter().println("⚠️ No student record found for user: " + username);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Database Error: " + e.getMessage());
        }
    }
}

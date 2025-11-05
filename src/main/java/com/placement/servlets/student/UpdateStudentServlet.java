package com.placement.servlets.student;

import com.placement.dao.StudentDAO;
import com.placement.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/student/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            // Collect data from form
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            boolean isWilling = Boolean.parseBoolean(req.getParameter("isWilling"));

            // Build student object
            Student student = new Student();
            student.setStudentId(studentId);
            student.setEmail(email);
            student.setPhoneNumber(phone);
            student.setWilling(isWilling);

            // Call DAO
            StudentDAO dao = new StudentDAO();
            boolean success = dao.updateStudent(student);

            if (success) {
                session.setAttribute("updateMsg", "✅ Profile updated successfully!");
            } else {
                session.setAttribute("updateMsg", "❌ Failed to update profile.");
            }

            // Redirect back to dashboard
            resp.sendRedirect(req.getContextPath() + "/student/dashboard?message=Profile updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("updateMsg", "❌ Error updating profile: " + e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/student/dashboard.jsp");
        }
    }
}

package com.placement.servlets;

import com.placement.dao.StudentDAO;
import com.placement.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/updateStudent")
public class AdminUpdateStudentServlet extends HttpServlet {
    private StudentDAO dao;

    @Override
    public void init() {
        dao = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=unauthorized");
            return;
        }

        try {
            int id = Integer.parseInt(req.getParameter("studentId"));
            String roll = req.getParameter("rollNumber");
            String name = req.getParameter("name");
            String department = req.getParameter("department");
            int year = Integer.parseInt(req.getParameter("year"));
            double cgpa = Double.parseDouble(req.getParameter("cgpa"));
            double tenth = Double.parseDouble(req.getParameter("tenthScore"));
            double twelfth = Double.parseDouble(req.getParameter("twelfthScore"));
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            boolean placed = "true".equalsIgnoreCase(req.getParameter("isPlaced")) || "on".equalsIgnoreCase(req.getParameter("isPlaced"));
            boolean willing = "true".equalsIgnoreCase(req.getParameter("isWilling")) || "on".equalsIgnoreCase(req.getParameter("isWilling"));
            String company = req.getParameter("company");

            Student s = new Student();
            s.setStudentId(id);
            s.setRollNumber(roll);
            s.setName(name);
            s.setDepartment(department);
            s.setYear(year);
            s.setCgpa(cgpa);
            s.setTenthScore(tenth);
            s.setTwelfthScore(twelfth);
            s.setEmail(email);
            s.setPhoneNumber(phone);
            s.setPlaced(placed);
            s.setWilling(willing);
            s.setCompany(company);

            boolean ok = dao.adminUpdateStudent(s);
            if (ok) {
                // success: redirect to refreshed admin dashboard
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard?msg=updated");
            } else {
                resp.sendRedirect(req.getContextPath() + "/admin/editStudent?id=" + id + "&error=updateFailed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/editStudent?id=" + req.getParameter("studentId") + "&error=exception");
        }
    }
}

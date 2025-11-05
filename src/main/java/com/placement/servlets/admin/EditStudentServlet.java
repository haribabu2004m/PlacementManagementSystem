package com.placement.servlets.admin;

import com.placement.dao.StudentDAO;
import com.placement.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/editStudent")
public class EditStudentServlet extends HttpServlet {
    private StudentDAO dao;

    @Override
    public void init() {
        dao = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Optional extra check: defense in depth (filter already protects /admin/*)
        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=unauthorized");
            return;
        }

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Student student = dao.getStudentById(id);
            if (student == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard?error=notfound");
                return;
            }
            req.setAttribute("student", student);
            RequestDispatcher rd = req.getRequestDispatcher("/admin/editStudent.jsp");
            rd.forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard?error=invalidId");
        }
    }
}

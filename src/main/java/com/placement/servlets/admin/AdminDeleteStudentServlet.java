package com.placement.servlets.admin;

import com.placement.dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/deleteStudent")
public class AdminDeleteStudentServlet extends HttpServlet {
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
            int id = Integer.parseInt(req.getParameter("id"));
            boolean ok = dao.deleteStudent(id);
            if (ok) {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard?msg=deleted");
            } else {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard?error=deleteFailed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard?error=exception");
        }
    }
}

package com.placement.servlets.admin;

import com.placement.dao.StudentDAO;
import com.placement.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/searchStudents")
public class AdminSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String department = req.getParameter("useDepartment") != null ? req.getParameter("department") : null;
        Integer year = req.getParameter("useYear") != null ? Integer.parseInt(req.getParameter("year")) : null;
        Double minCgpa = req.getParameter("useCgpa") != null ? Double.parseDouble(req.getParameter("cgpa")) : null;
        Boolean isPlaced = req.getParameter("usePlaced") != null ? Boolean.parseBoolean(req.getParameter("placed")) : null;
        Boolean isWilling = req.getParameter("useWilling") != null ? Boolean.parseBoolean(req.getParameter("isWilling")) : null;

        List<Student> filtered = new StudentDAO().searchStudents(department, year, minCgpa, isPlaced, isWilling);

        req.setAttribute("students", filtered);
        req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
    }
}

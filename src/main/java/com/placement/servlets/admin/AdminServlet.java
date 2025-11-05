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


@WebServlet("/admin/dashboard")
public class AdminServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            StudentDAO dao = new StudentDAO();
            List<Student> students=dao.getAllStudents();

            req.setAttribute("students",students);
            req.getRequestDispatcher("/admin/dashboard.jsp").forward(req,resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error loading student data: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}

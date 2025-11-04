package com.placement.servlets;

import com.placement.dao.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try(Connection conn= DBConnection.getConnection()){
            String sql = "SELECT * from users where username = ? and password = ? ";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String role = rs.getString("role");
                HttpSession session= req.getSession();
                session.setAttribute("username",username);
                session.setAttribute("role",role);

                if ("admin".equalsIgnoreCase(role)){
                    resp.sendRedirect("admin/dashboard");
                }
                else{
                    resp.sendRedirect("student/dashboard");
                }
            } else {
                req.setAttribute("errorMessage", "Invalid username or password!");
                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Database error: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req, resp);
        }
    }
}

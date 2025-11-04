
package com.placement.controller;

import com.placement.dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBInsertSelectTest {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                System.out.println("❌ Database connection failed!");
                return;
            }

            System.out.println("✅ Database connected successfully!");

            // Step 1: Insert a test record
            String insertSQL = "INSERT INTO users (user_name, password, role) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertSQL)) {
                ps.setString(1, "test_user");
                ps.setString(2, "password123");
                ps.setString(3, "student");
                ps.executeUpdate();
                System.out.println("✅ Insert successful!");
            }

            // Step 2: Select all users
            String selectSQL = "SELECT user_id, user_name, role FROM users";
            try (PreparedStatement ps = con.prepareStatement(selectSQL);
                 ResultSet rs = ps.executeQuery()) {

                System.out.println("\n📋 Users in Database:");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("user_id")
                            + " | Username: " + rs.getString("user_name")
                            + " | Role: " + rs.getString("role"));
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        }
    }
}

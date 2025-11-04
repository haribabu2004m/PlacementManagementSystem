package com.placement.controller;

import com.placement.dao.DBConnection;
import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();
        if (con != null) {
            System.out.println("✅ PostgreSQL connection test successful!");
        } else {
            System.out.println("❌ PostgreSQL connection test failed!");
        }
    }
}

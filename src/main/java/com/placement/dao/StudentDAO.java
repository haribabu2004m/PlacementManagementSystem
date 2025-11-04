package com.placement.dao;

import com.placement.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public Student getStudentById(int id) {
        Student student = null;
        String query = " select * from students where student_id=?";


        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student();
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
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    public Student getStudentByName(String username) throws SQLException {
        Student student = null;
        String query = """
                select s.* from students s
                join users u  
                on s.user_id=u.user_id 
                where u.username=?
                """;


        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student();
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
            }


        }
        return student;
    }


    public boolean updateStudent(Student student) {
        String query = """
                UPDATE students 
                SET email = ?, phone_number = ?, is_willing = ?, last_updated = CURRENT_TIMESTAMP
                WHERE student_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getEmail());
            stmt.setString(2, student.getPhoneNumber());
            stmt.setBoolean(3, student.isWilling());
            stmt.setInt(4, student.getStudentId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Student> getAllStudents() {
        List<Student> lists = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "select * from students order by roll_number asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {



                lists.add(mapResultSetToStudent(rs));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lists;

    }


    public boolean adminUpdateStudent(Student student) {
        String query = """
            UPDATE students 
            SET name = ?, year = ?, department = ?, cgpa = ?, 
                tenth_score = ?, twelfth_score = ?, email = ?, phone_number = ?, 
                is_willing = ?, is_placed = ?, company = ?, last_updated = CURRENT_TIMESTAMP
            WHERE student_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getYear());
            stmt.setString(3, student.getDepartment());
            stmt.setDouble(4, student.getCgpa());
            stmt.setDouble(5, student.getTenthScore());
            stmt.setDouble(6, student.getTwelfthScore());
            stmt.setString(7, student.getEmail());
            stmt.setString(8, student.getPhoneNumber());
            stmt.setBoolean(9, student.isWilling());
            stmt.setBoolean(10, student.isPlaced());
            stmt.setString(11, student.getCompany());
            stmt.setInt(12, student.getStudentId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteStudent(int id) {
        String query = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> searchStudents(String department, Integer year, Double minCgpa, Boolean isPlaced, Boolean isWilling) {
        List<Student> list = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM students WHERE 1=1");

        if (department != null) query.append(" AND department = ?");
        if (year != null) query.append(" AND year = ?");
        if (minCgpa != null) query.append(" AND cgpa >= ?");
        if (isPlaced != null) query.append(" AND is_placed = ?");
        if (isWilling != null) query.append(" AND is_willing = ?");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {

            int i = 1;
            if (department != null) ps.setString(i++, department);
            if (year != null) ps.setInt(i++, year);
            if (minCgpa != null) ps.setDouble(i++, minCgpa);
            if (isPlaced != null) ps.setBoolean(i++, isPlaced);
            if (isWilling != null) ps.setBoolean(i++, isWilling);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSetToStudent(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    // Utility method to map a ResultSet row to a Student object
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setStudentId(rs.getInt("student_id"));
        s.setRollNumber(rs.getString("roll_number"));
        s.setName(rs.getString("name"));
        s.setYear(rs.getInt("year"));
        s.setDepartment(rs.getString("department"));
        s.setCgpa(rs.getDouble("cgpa"));
        s.setTenthScore(rs.getDouble("tenth_score"));
        s.setTwelfthScore(rs.getDouble("twelfth_score"));
        s.setEmail(rs.getString("email"));
        s.setPhoneNumber(rs.getString("phone_number"));
        s.setWilling(rs.getBoolean("is_willing"));
        s.setPlaced(rs.getBoolean("is_placed"));
        s.setCompany(rs.getString("company"));
        s.setLastUpdated(rs.getTimestamp("last_updated"));
        return s;
    }


}

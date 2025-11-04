package com.placement.model;

import java.sql.Timestamp;  // ✅ for last_updated (PostgreSQL TIMESTAMP)

public class Student {
    private int studentId;
    private String rollNumber;
    private String name;
    private int year;
    private String department;
    private double cgpa;
    private double tenthScore;
    private double twelfthScore;
    private String email;
    private String phoneNumber;
    private boolean isWilling;
    private boolean isPlaced;
    private String company;
    private Timestamp lastUpdated; // ✅ new field added

    // ✅ Getters & Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public double getTenthScore() { return tenthScore; }
    public void setTenthScore(double tenthScore) { this.tenthScore = tenthScore; }

    public double getTwelfthScore() { return twelfthScore; }
    public void setTwelfthScore(double twelfthScore) { this.twelfthScore = twelfthScore; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public boolean isWilling() { return isWilling; }
    public void setWilling(boolean isWilling) { this.isWilling = isWilling; }

    public boolean isPlaced() { return isPlaced; }
    public void setPlaced(boolean isPlaced) { this.isPlaced = isPlaced; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public Timestamp getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(Timestamp lastUpdated) { this.lastUpdated = lastUpdated; }
}

<%@ page import="com.placement.model.Student" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // ✅ Admin role verification (failsafe — filter already protects this)
    String role = (String) session.getAttribute("role");
    if (role == null || !"admin".equals(role)) {
        response.sendRedirect(request.getContextPath() + "/login.jsp?error=unauthorized");
        return;
    }

    // ✅ Retrieve student object passed by servlet
    Student student = (Student) request.getAttribute("student");
    if (student == null) {
        out.println("<p class='text-danger text-center mt-5'>❌ No student data found.</p>");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Student | Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <span class="navbar-brand">Admin - Edit Student</span>
        <form action="<%=request.getContextPath()%>/logout" method="post" class="d-flex">
            <button class="btn btn-light btn-sm" type="submit">Logout</button>
        </form>
    </div>
</nav>

<div class="container mt-4">
    <h3>Edit Student: <%= student.getName() %> (<%= student.getRollNumber() %>)</h3>
    <hr>

    <form action="<%= request.getContextPath() %>/admin/updateStudent" method="post" class="mt-3">
        <input type="hidden" name="studentId" value="<%= student.getStudentId() %>">

        <div class="row">
            <div class="col-md-6 mb-3">
                <label class="form-label">Roll Number</label>
                <input type="text" class="form-control" value="<%= student.getRollNumber() %>" readonly>
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Name</label>
                <input type="text" class="form-control" name="name" value="<%= student.getName() %>">
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Department</label>
                <input type="text" class="form-control" name="department" value="<%= student.getDepartment() %>">
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Year</label>
                <input type="number" class="form-control" name="year" value="<%= student.getYear() %>">
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">10th Score</label>
                <input type="text" class="form-control" name="tenthScore" value="<%= student.getTenthScore() %>">
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">12th Score</label>
                <input type="text" class="form-control" name="twelfthScore" value="<%= student.getTwelfthScore() %>">
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">CGPA</label>
                <input type="text" class="form-control" name="cgpa" value="<%= student.getCgpa() %>">
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Placement Willingness</label>
                <select class="form-select" name="isWilling">
                    <option value="true" <%= student.isWilling() ? "selected" : "" %>>Willing</option>
                    <option value="false" <%= !student.isWilling() ? "selected" : "" %>>Not Willing</option>
                </select>
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Placement Status</label>
                <select class="form-select" name="isPlaced">
                    <option value="true" <%= student.isPlaced() ? "selected" : "" %>>Placed</option>
                    <option value="false" <%= !student.isPlaced() ? "selected" : "" %>>Not Placed</option>
                </select>
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Company</label>
                <input type="text" class="form-control" name="company" value="<%= student.getCompany() != null ? student.getCompany() : "" %>">
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Email</label>
                <input type="email" class="form-control" name="email" value="<%= student.getEmail() %>">
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Phone</label>
                <input type="text" class="form-control" name="phone" value="<%= student.getPhoneNumber() %>">
            </div>
        </div>

        <div class="d-flex justify-content-between mt-4">
            <a href="<%= request.getContextPath() %>/admin/dashboard" class="btn btn-secondary">⬅ Back</a>
            <button type="submit" class="btn btn-success">💾 Update Student</button>
        </div>
    </form>
</div>

</body>
</html>

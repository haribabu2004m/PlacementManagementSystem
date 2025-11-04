<%@ page import="com.placement.model.Student" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // ✅ Ensure session exists
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // ✅ Get student details (set by StudentServlet)
    Student student = (Student) request.getAttribute("student");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard | Placement Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <span class="navbar-brand">Placement Management - Student Panel</span>
        <form action="<%=request.getContextPath()%>/logout" method="post" class="d-flex">
            <button class="btn btn-light btn-sm" type="submit">Logout</button>
        </form>
    </div>
</nav>

<div class="container mt-4">

    <c:if test="${not empty sessionScope.updateMsg}">
        <p>${sessionScope.updateMsg}</p>
        <c:remove var="updateMsg" scope="session"/>
    </c:if>

    <h3>Welcome, <%= username %> 👋</h3>
    <hr>

    <% if (student != null) { %>
    <form action="<%= request.getContextPath() %>/student/updateStudent" method="post" class="mt-3">
        <input type="hidden" name="studentId" value="<%= student.getStudentId() %>">

        <div class="row">
            <div class="col-md-6 mb-3">
                <label class="form-label">Roll Number</label>
                <input type="text" class="form-control" value="<%= student.getRollNumber() %>" readonly>
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label">Name</label>
                <input type="text" class="form-control" value="<%= student.getName() %>" readonly>
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Department</label>
                <input type="text" class="form-control" value="<%= student.getDepartment() %>" readonly>
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label">Year</label>
                <input type="text" class="form-control" value="<%= student.getYear() %>" readonly>
            </div>

            <!-- ✅ Added 10th and 12th score display -->
            <div class="col-md-6 mb-3">
                <label class="form-label">10th Score</label>
                <input type="text" class="form-control" value="<%= student.getTenthScore() %>" readonly>
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label">12th Score</label>
                <input type="text" class="form-control" value="<%= student.getTwelfthScore() %>" readonly>
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">CGPA</label>
                <input type="text" class="form-control" value="<%= student.getCgpa() %>" readonly>
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label">Company (if placed)</label>
                <input type="text" class="form-control" value="<%= student.getCompany() != null ? student.getCompany() : "Not Placed" %>" readonly>
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Email</label>
                <input type="email" class="form-control" name="email" value="<%= student.getEmail() %>">
            </div>
            <div class="col-md-6 mb-3">
                <label class="form-label">Phone Number</label>
                <input type="text" class="form-control" name="phone" value="<%= student.getPhoneNumber() %>">
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label">Placement Willingness</label>
                <select class="form-select" name="isWilling">
                    <option value="true" <%= student.isWilling() ? "selected" : "" %>>Willing</option>
                    <option value="false" <%= !student.isWilling() ? "selected" : "" %>>Not Willing</option>
                </select>
            </div>
        </div>

        <div class="d-grid mt-3">
            <button type="submit" class="btn btn-success">Update Details</button>
        </div>
    </form>
    <% } else { %>
        <p class="text-danger mt-4">❌ No student details found.</p>
    <% } %>
</div>
</body>
</html>

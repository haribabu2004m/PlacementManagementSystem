<%@ page import="java.util.List" %>
<%@ page import="com.placement.model.Student" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String username = (String) session.getAttribute("username");
    List<Student> students = (List<Student>) request.getAttribute("students");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard | Placement Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <span class="navbar-brand">Placement Management - Admin Panel</span>
        <form action="<%=request.getContextPath()%>/logout" method="post" class="d-flex">
            <button class="btn btn-light btn-sm" type="submit">Logout</button>
        </form>
    </div>
</nav>

<div class="container mt-4">
    <h3>Welcome, Admin <%= username %> 👋</h3>
    <div class="card shadow-sm mb-4">
      <div class="card-body">
        <form action="<%= request.getContextPath() %>/admin/searchStudents" method="get" class="row g-3">
          <!-- Department Filter -->
          <div class="col-md-3">
            <input type="checkbox" name="useDepartment" id="useDepartment">
            <label for="department">Department:</label>
            <select name="department" class="form-select">
              <option value="">-- Select --</option>
              <option value="CS">CSE</option>
              <option value="ECE">ECE</option>
              <option value="Mechanical">MECH</option>
              <option value="CIVIL">CIVIL</option>
              <option value="IT">IT</option>

            </select>
          </div>

          <!-- Year Filter -->
          <div class="col-md-2">
            <input type="checkbox" name="useYear" id="useYear">
            <label for="year">Year:</label>
            <select name="year" class="form-select">
              <option value="">-- Select --</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
            </select>
          </div>

          <!-- CGPA Filter -->
          <div class="col-md-2">
            <input type="checkbox" name="useCgpa" id="useCgpa">
            <label for="cgpa">Min CGPA:</label>
            <input type="number" step="0.01" name="cgpa" class="form-control" placeholder="e.g. 8.0">
          </div>

          <!-- Placement Filter -->
          <div class="col-md-2">
            <input type="checkbox" name="usePlaced" id="usePlaced">
            <label for="placed">Placed:</label>
            <select name="placed" class="form-select">
              <option value="">-- Select --</option>
              <option value="true">Placed</option>
              <option value="false">Not Placed</option>
            </select>
          </div>

          <!-- Willingness Filter -->
          <div class="col-md-2">
            <input type="checkbox" name="useWilling" id="useWilling">
            <label for="isWilling">Willing:</label>
            <select name="isWilling" class="form-select">
              <option value="">-- Select --</option>
              <option value="true">Willing</option>
              <option value="false">Not Willing</option>
            </select>
          </div>

          <!-- Submit -->
          <div class="col-md-1 d-flex align-items-end">
            <button type="submit" class="btn btn-primary w-100">Filter</button>
          </div>
        </form>
      </div>
    </div>

    <hr>

    <h5>All Student Records</h5>
    <table class="table table-bordered table-hover mt-3">
        <thead class="table-primary">
            <tr>
                <th>ID</th>
                <th>Roll No</th>
                <th>Name</th>
                <th>Dept</th>
                <th>Year</th>
                <th>CGPA</th>
                <th>10th</th>
                <th>12th</th>
                <th>Placed</th>
                <th>Company</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Last Updated</th>
                <th>Actions</th> <!-- we'll use this soon for Edit/Delete -->
            </tr>
        </thead>
        <tbody>
        <% if (students != null && !students.isEmpty()) {
            for (Student s : students) { %>
                <tr>
                    <td><%= s.getStudentId() %></td>
                    <td><%= s.getRollNumber() %></td>
                    <td><%= s.getName() %></td>
                    <td><%= s.getDepartment() %></td>
                    <td><%= s.getYear() %></td>
                    <td><%= s.getCgpa() %></td>
                    <td><%= s.getTenthScore() %></td>
                    <td><%= s.getTwelfthScore() %></td>
                    <td><%= s.isPlaced() ? "✅" : "❌" %></td>
                    <td><%= s.getCompany() != null ? s.getCompany() : "-" %></td>
                    <td><%= s.getEmail() %></td>
                    <td><%= s.getPhoneNumber() %></td>
                    <td><%= s.getLastUpdated() %></td>

                    <!-- ✅ Action buttons (will activate soon) -->
                    <td>
                        <a href="<%=request.getContextPath()%>/admin/editStudent?id=<%= s.getStudentId() %>"
                           class="btn btn-sm btn-primary">Edit</a>
                        <form action="<%=request.getContextPath()%>/admin/deleteStudent" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= s.getStudentId() %>"/>
                            <button type="submit" class="btn btn-sm btn-danger"
                                    onclick="return confirm('Are you sure you want to delete <%= s.getName() %>?');">Delete</button>
                        </form>
                    </td>
                </tr>
        <%  }
        } else { %>
            <tr><td colspan="14" class="text-center text-danger">No students found.</td></tr>
        <% } %>
        </tbody>


    </table>
</div>
</body>
</html>

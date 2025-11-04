# 🎓 Placement Management System

A web-based application built using **Java Servlets**, **JSP**, and **PostgreSQL**, designed to simplify placement record management for colleges.  
It supports two roles — **Admin (Placement Officer)** and **Students**, with separate dashboards and access controls.

---

## 🚀 Features

### 👨‍💼 Admin (Placement Officer)
- Secure login and authentication using session filters.
- View all student details with CGPA, department, and placement status.
- Edit or delete student records directly from the dashboard.
- Filter students by:
  - Department
  - CGPA
  - Year
  - Placement Status (Placed / Not Placed)
  - Willingness to participate in placements
- Manage company placement information for students.

### 🎓 Student
- Secure login for individual student accounts.
- View personal academic and placement details.
- Update editable fields (email, phone number, placement willingness).
- View company details if placed.

---

## 🧠 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Frontend** | JSP, HTML5, CSS3, Bootstrap 5 |
| **Backend** | Java Servlets (Jakarta EE) |
| **Database** | PostgreSQL |
| **Server** | Apache Tomcat |
| **IDE** | IntelliJ IDEA Community Edition |
| **Build Tool** | Maven |

---

## 📁 Project Structure

src/
├── main/
│ ├── java/com/placement/
│ │ ├── controller/ # Optional (future use)
│ │ ├── dao/ # DB connection & queries
│ │ ├── filters/ # Session & role filters
│ │ ├── model/ # JavaBeans (Student, User)
│ │ └── servlets/ # Core Servlets (Admin, Student)
│ └── webapp/
│ ├── admin/ # Admin JSP pages
│ ├── student/ # Student JSP pages
│ ├── WEB-INF/web.xml # Servlet mappings
│ ├── login.jsp
│ ├── index.jsp
│ └── logout.jsp
└── pom.xml



## ⚙️ Setup Instructions

### 1️⃣ Clone the repository

git clone https://github.com/haribabu2004m/PlacementManagementSystem.git
cd PlacementManagementSystem


🗄️ Database: placementdb

Table:users

| Column Name | Data Type   | Constraints / Description             |
| ----------- | ----------- | ------------------------------------- |
| user_id   | SERIAL      | Primary Key                             |
| username  | VARCHAR(50) | Username of the user                    |
| password  | TEXT        | Hashed password                         |
| role      | VARCHAR(20) | Role of user → admin or student         |

Table:students

| Column Name     | Data Type    | Constraints / Description                    |
| --------------- | ------------ | -------------------------------------------- |
| student_id    | SERIAL       | Primary Key                                    |
| roll_number   | VARCHAR(20)  | Unique roll number                             |
| name          | VARCHAR(100) | Student’s full name                            |
| year          | INT          | Year of study                                  |
| department    | VARCHAR(50)  | Department name                                |
| cgpa          | DECIMAL(3,2) | CGPA value                                     |
| tenth_score   | DECIMAL(4,2) | 10th exam score (in %)                         |
| twelfth_score | DECIMAL(4,2) | 12th exam score (in %)                         |
| email         | VARCHAR(100) | Student’s email ID                             |
| phone_number  | VARCHAR(15)  | Contact number                                 |
| is_willing    | BOOLEAN      | Default TRUE  indicates placement interest     |
| is_placed     | BOOLEAN      | Default FALS  indicates placement status       |
| company       | VARCHAR(100) | Company name if placed                         |
| last_updated  | TIMESTAMP    | Default CURRENT_TIMESTAMP                      |

<img width="1024" height="1536" alt="image" src="https://github.com/user-attachments/assets/cd3222ce-55f0-4249-a092-b93d1aa61d7f" />


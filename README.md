# Student-Management-System


## Overview
This is a simple Java (AWT) Student Management desktop application that uses MySQL for storage.

This README explains:
- how to open & run the project in **Visual Studio Code**
- how to add the MySQL JDBC driver
- how to create the database and table(s) used by the app
- the JDBC connection string example you must place in the code

> Repo: (your project) — I inspected the repo to prepare instructions. :contentReference[oaicite:0]{index=0}

---

## Prerequisites

1. **Java JDK 11+** installed and `javac` / `java` on your PATH.  
2. **MySQL Server** (local or remote).  
3. **VS Code** with the Java Extension Pack (optional but recommended).  
4. Download **MySQL Connector/J** (JDBC driver) — you will add the JAR to the project classpath. See MySQL Connector/J documentation for details. :contentReference[oaicite:1]{index=1}

---

## Database: schema & table(s)

Run these SQL commands in MySQL (e.g., using MySQL Workbench or `mysql` CLI).  
The instructions below create a database called `studentdb` and a table called `student`. If your code expects a different DB or table name (for example `students`), I included a `students` variant below — create whichever matches your source code.

### Create database (run once)
```sql
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;
CREATE TABLE IF NOT EXISTS student (
  s_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100),
  dob DATE,
  gender VARCHAR(10),
  address VARCHAR(255),
  phone VARCHAR(20),
  email VARCHAR(150),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
Class.forName("com.mysql.cj.jdbc.Driver"); // optional for newer JDBC
String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
String user = "root";
String pass = "your_db_password";
Connection con = DriverManager.getConnection(url, user, pass);


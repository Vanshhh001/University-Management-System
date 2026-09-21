# University Management System

A Java Swing desktop application for managing university records, including student and faculty details, attendance/leave, fees, examinations, results, and user login accounts.

> This is currently a **desktop application**. Run it locally with Java and MySQL. It must be converted to a web application before it can be deployed as a website on Render.

## Features

- Create separate login accounts with Sign Up
- Add, view, update, and remove student records
- Add, view, update, and remove faculty records
- Generate sequential IDs
  - Student roll numbers: `1000020000`, `1000020001`, ...
  - Faculty employee IDs: `1000010000`, `1000010001`, ...
- Validate data entry
  - Phone numbers: 10 digits
  - Aadhaar numbers: 12 digits
  - Class percentages: 0–100
  - Subject marks: whole numbers from 0–100
- Record faculty and student leave
- Configure fee structures and collect student fees
- Enter subject-wise marks by semester
- View a structured examination report with total, percentage, and result status

## Result Rules

A student is marked **PASS** when:

- Total marks are at least `200 / 500`, and
- Every subject has at least `33 / 100`.

Otherwise, the result is **FAIL**. A result is **PENDING** only when no marks have been entered for the selected semester.

## Technology

| Area | Technology |
| --- | --- |
| Language | Java |
| User interface | Java Swing |
| Database | MySQL |
| Database driver | MySQL Connector/J 8.0.28 |
| Date picker | JCalendar |
| IDE | IntelliJ IDEA (recommended) |

## Project Structure

```text
src/
├── University/Management/System/
│   ├── Login.java                 # Login screen
│   ├── Signup.java                # Account registration screen
│   ├── main_class.java            # Main dashboard/menu
│   ├── AddStudent.java            # Student registration
│   ├── AddFaculty.java            # Faculty registration
│   ├── EnterMarks.java            # Semester marks entry
│   ├── Marks.java                 # Structured examination report
│   ├── Conn.java                  # MySQL connection configuration
│   └── ...
└── icon/                          # Application images
```

## Prerequisites

Install the following before running the project:

- Java Development Kit (JDK) 8 or later
- MySQL Server 8 or later
- IntelliJ IDEA Community or Ultimate Edition
- MySQL Connector/J
- JCalendar library
- ResultSet2xml library

The project currently references these JAR files from the `Downloads` folder. In IntelliJ, add them under **File → Project Structure → Libraries** if they are missing:

```text
mysql-connector-java-8.0.28.jar
jcalendar-tz-1.3.3-4.jar
ResultSet2xml.jar
```

## Database Setup

1. Start MySQL Server.
2. Create the application database:

```sql
CREATE DATABASE universitymanagement;
USE universitymanagement;
```

3. Create the login table:

```sql
CREATE TABLE login (
    username VARCHAR(30) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);
```

4. Create the remaining application tables (`student`, `teacher`, `subject`, `marks`, `studentLeave`, `teacherLeave`, `feecollege`, and fee-structure tables) using your existing project database schema.

5. Update the database connection in [`Conn.java`](src/University/Management/System/Conn.java):

```java
connection = DriverManager.getConnection(
    "jdbc:mysql:///universitymanagement",
    "YOUR_MYSQL_USERNAME",
    "YOUR_MYSQL_PASSWORD"
);
```

Do not commit real database passwords if you publish this repository online.

## Run Locally

1. Open the project folder in IntelliJ IDEA.
2. Confirm that the required JAR files are attached as project libraries.
3. Configure MySQL in `Conn.java`.
4. Build the project with **Build → Build Project**.
5. Run [`splash.java`](src/University/Management/System/splash.java), or run [`Login.java`](src/University/Management/System/Login.java) directly.
6. Select **Sign Up** to create an account, then log in.

## Typical Workflow

1. Create a login account.
2. Add faculty and student details.
3. Enter marks for a student and semester.
4. Open **Examination Results** from the main menu.
5. Select a student and semester to view the report.

## Deployment Note

Render hosts web services, while this project uses Java Swing windows. To deploy it publicly, migrate the UI to a web framework such as Spring Boot with Thymeleaf or React, then connect it to a hosted database.

## Future Improvements

- Store password hashes instead of plain-text passwords
- Use environment variables for database credentials
- Add role-based access (Admin, Faculty, Student)
- Add printable/PDF result reports
- Add database migrations and a complete schema file
- Convert the desktop application into a Spring Boot web application

## Author

Vansh Saxena

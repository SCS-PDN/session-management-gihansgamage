<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Dashboard</title>
</head>
<body>
    <h1>Welcome, ${sessionScope.username}!</h1>
    <a href="LogoutServlet">Logout</a>
    
    <h2>Available Courses</h2>
    <table border="1">
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.courseId}</td>
                <td>${course.courseName}</td>
                <td>${course.instructor}</td>
                <td>
                    <c:choose>
                        <c:when test="${enrolledCourses.contains(course.courseId)}">
                            Enrolled
                        </c:when>
                        <c:otherwise>
                            <a href="EnrollServlet?courseId=${course.courseId}">Enroll</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Your Enrolled Course IDs</h2>
    <ul>
        <c:forEach items="${enrolledCourses}" var="courseId">
            <li>${courseId}</li>
        </c:forEach>
    </ul>
</body>
</html>

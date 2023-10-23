<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Information</title>
</head>

<body>
    <h1>Teacher Information</h1>
    <p><strong>Teacher Name:</strong> <%= request.getAttribute("teacherName") %></p>
    <p><strong>Class Name:</strong> <%= request.getAttribute("className") %></p>
    <p><strong>Subject Name:</strong> <%= request.getAttribute("subjectName") %></p>
</body>

</html>
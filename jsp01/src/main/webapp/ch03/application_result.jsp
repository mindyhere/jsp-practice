<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String message = (String) application.getAttribute("message");
	out.println("메세지 : " + message + "<br>");
	%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login result ex.</title>
</head>
<body>
	<%
	String result = (String) request.getAttribute("result");
	out.println(result);
	%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result</title>
</head>
<body>
	<%
	int num = Integer.valueOf(request.getParameter("num"));
	for (int i = 1; i <= 9; i++) {
		out.println(num + " X " + i + " = " + num * i + "<br>");
	}
	%>
</body>
</html>
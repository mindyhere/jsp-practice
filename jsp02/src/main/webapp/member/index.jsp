<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member ex.</title>
</head>
<body>
	<!-- index.jsp 시작페이지 → model : Controller.java → list.jsp 결과페이지(출력) -->
	<%
	response.sendRedirect("/jsp02/member_servlet/list.do");
	%>
</body>
</html>
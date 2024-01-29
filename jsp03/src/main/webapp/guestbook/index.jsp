<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guestbook index</title>
</head>
<body>
	<%
	String context = request.getContextPath(); //프로젝트이름
	response.sendRedirect(context + "/gb_servlet/list.do");
	%>
</body>
</html>
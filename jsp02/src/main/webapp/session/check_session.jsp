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
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
	%>
	<script>
		alert("로그인하세요");
		location.href = "/jsp02/session/login.jsp";
	</script>
	<%
	}
	%>
</body>
</html>
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
	response.addCookie(new Cookie("id", "park"));
	%>
	쿠키를 수정했습니다.
	<br>
	<a href="view_cookie.jsp">쿠키확인</a>
</body>
</html>
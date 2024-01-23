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
	Cookie cookie = new Cookie("id", "");
	cookie.setMaxAge(0); //유효시간 0초 → 즉시 삭제
	response.addCookie(cookie);	//없으면 생성, 있으면 수정
	%>
	쿠키가 삭제되었습니다.
	<br>
	<a href="view_cookie.jsp">쿠키확인</a>
</body>
</html>
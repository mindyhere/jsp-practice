<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
	에러 처리가 되어있지 않았을 때, 관련정보 및 코드가 화면에 출력됨
	실행 시 → NumberFormatException: Cannot parse null string
	 -->
	<%
	int a = Integer.parseInt(request.getParameter("num"));
	%>
	a :
	<%=a%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>세션의 유효시간 설정</h2>
	<%
	// session.setMaxInactiveInterval(600) → 유효시간 설정(초단위, 600초=10분)
	// 기본값 : 1800초(30분)
	int timeout = session.getMaxInactiveInterval();
	out.println("timeout : " + timeout); // 초단위
	%>
</body>
</html>
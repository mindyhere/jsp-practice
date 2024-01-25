<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- taglib prefix="접두어" uri="JSTL라이브러리의 URI(ctrl+space 확인)" -->
<!-- 태그에서 사용할 때는 → 접두어(prefix, <접두어:태그>)로 사용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>4.2. ex01_result</title>
</head>
<body>
	<%
	if (request.getParameter("num") != null) {
		int num = Integer.parseInt(request.getParameter("num"));
		int sum = 0;

		for (int i = 1; i <= num; i++) {
			sum += i;
		}
		out.println("합계 : " + sum);
	} else {
		response.sendRedirect("ex01.jsp");
	}
	%>
	<hr>
	<c:set var="sum" value="0" />
	
	<c:forEach var="i" begin="1" end="${param.num }">
	<!-- 반복문 c:forEach var="개별변수" items="집합" begin="시작" end="종료" -->
		
		<c:set var="sum" value="${sum+i }" />
		<!-- 변수선언 c:set, var="개별변수",  value="값" -->
	</c:forEach>
	합계 : ${sum }
</body>
</html>
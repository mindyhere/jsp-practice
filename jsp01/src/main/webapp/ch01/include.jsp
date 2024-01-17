<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 클래스를 참조 -->
<%@ page import="common.Constants"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jsp 페이지를 참조 -->
<%@ include file="sub.jsp"%>
<style>
body {
	background: <%=background%>;
}
</style>
</head>
<body>

	<h2>
		max:
		<%=Constants.MAX%></h2>
	<h2>
		msg:
		<%=msg%>
	</h2>
</body>
</html>
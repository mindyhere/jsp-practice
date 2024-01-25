<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>4.4. ex02_result</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>

	<%
	if (request.getAttribute("items") != null) {
	List<String> items = (List<String>) request.getAttribute("items");
		for (String str : items) {
			out.println(str + "<br>");
		}
	} else {
		response.sendRedirect("ex02.jsp");
	}
	%>
	<hr>
	<!-- 			개별			   집합 -->
	<c:forEach var="fruit" items="${items }">
		${fruit }<br>
	</c:forEach>
</body>
</html>
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
	for (int i = 1; i <= 150; i++) {
		String color = (i % 2 == 0 ? "red" : "blue");
		//		        2로 나눈 나머지  true	  false
	%>

	<p style="font-size:<%=i%>px ; color:<%=color%>">hello</p>
	<% } %>
</body>
</html>
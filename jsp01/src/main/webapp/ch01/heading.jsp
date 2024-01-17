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
	for (int i = 6; i >= 1; i--) {
		out.println("<h" + i + ">Heading</h" + i + ">");
	}
	%>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<br>
	<%
	String[] fruits = { "apple", "peach", "grape", "orange" };
	//String[] fruits=null;
	%>

	<ul>
		<%
		for (String fruit : fruits) {
			//개별 			배열
		%>
		<li><%=fruit%></li>
		<%
		}
		%>
	</ul>

</body>
</html>
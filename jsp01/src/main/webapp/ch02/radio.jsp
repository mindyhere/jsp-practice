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
	request.setCharacterEncoding("utf-8"); /* 톰캣 버전 호환성을 위해 추가 */
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	%>
	name:<%=name%><br> 
	gender:<%=gender%>

</body>
</html>
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
	request.setCharacterEncoding("utf-8"); //하위 호완성을 위해 추가(톰캣 10 이하)
	String str = request.getParameter("str");
	str = str.replace("<", "&lt;");
	str = str.replace(">", "&gt;");
	str = str.replace("\n", "<br>");
	str = str.replace("  ", "&nbsp;&nbsp;");
	%>
	opinion :
	<br>
	<%=str%>
</body>
</html>
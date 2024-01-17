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
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String color = request.getParameter("color");
	String str = "";

	if (color.equals("blue")) {
		str = "파랑";
	} else if (color.equals("green")) {
		str = "초록";
	} else if (color.equals("red")) {
		str = "빨강";
	}
	%>
	<%=name%>님이 선택한 색상은
	<%=str%>입니다.
	<div style="background:<%=color%>; width:300px;height:300px"></div>
</body>
</html>
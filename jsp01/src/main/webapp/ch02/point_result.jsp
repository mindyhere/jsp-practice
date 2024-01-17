<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Map map = (Map) request.getAttribute("point");
	String name = (String) map.get("name");
	int kor = (int) map.get("kor");
	int eng = (int) map.get("eng");
	int mat = (int) map.get("mat");
	int total = (int) map.get("total");
	double average = (double) map.get("average");
	%>

	이름 : <%=name %><br> 
	국어 : <%=kor %><br> 
	영어 : <%=eng %><br> 
	수학 : <%=mat %><br> 
	총점 : <%=total %><br> 
	평균 : <%=String.format("%.1f", average) %><br>
</body>
</html>
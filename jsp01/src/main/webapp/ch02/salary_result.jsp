<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Map map = (Map) request.getAttribute("salary");
	String name = (String) map.get("name");
	int basic = (int) map.get("basic");
	int bonus = (int) map.get("bonus");
	int total = (int) map.get("total");
	int tax = (int) map.get("tax");
	int money = (int) map.get("money");
	%>

	이름: <%=name %><br>
	기본급: <%=basic %><br>
	보너스: <%=bonus %><br>
	총액: <%=total %><br>
	세액: <%=tax %><br>
	실수령액: <%=money %><br>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2.6. ex04_result.jsp</title>
</head>
<body>
	<%@ page import="java.util.HashMap"%>
	<%
	HashMap<String, String> map = (HashMap<String, String>) request.getAttribute("map");
	String[] names = { "kim", "park", "song", "hyun" };

	for (String name : names) {
		out.println(name + " → " + map.get(name) + "<br>");
	}
	%>
	<hr>
	<!-- 맵['변수명'] ↔ 맵.변수명 -->
	${map["kim"] }
	<br> ${map["park"] }
	<br> ${map["song"] }
	<br> ${map["hyun"] }
	<br> ${map.kim }
	<br> ${map.park }
	<br> ${map.song }
	<br> ${map.hyun }
	<br>
</body>
</html>
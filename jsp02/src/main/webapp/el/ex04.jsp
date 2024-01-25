<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2.5. ex04</title>
</head>
<body>
	<%@ page import="java.util.Map"%>
	<%@ page import="java.util.HashMap"%>
	<%
	Map<String, String> map = new HashMap<>();
	map.put("kim", "김철수");
	// 추가	 변수명	  값
	map.put("park", "박희정");
	map.put("song", "송영미");
	map.put("hyun", "현정수");
	request.setAttribute("map", map);	// 저장, 다음페이지
	%>
	<jsp:forward page="ex04_result.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2.4. ex03_result</title>
</head>
<body>
<!-- session.getAttribute("변수명") ↔ ${sessionScope.변수 } : 사용자단위(로그인~로그아웃) -->
	이름: ${sessionScope.name }<br>
	나이: ${sessionScope.age }<br>
	직업: ${sessionScope.job }<br>
</body>
</html>
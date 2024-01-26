<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>input</title>
<link rel="shortcut icon" href="../images/favicon.ico"> 
</head>
<body>
	<h2>Survey</h2>
	<h2>${dto.question }</h2>
	<form method="post" action="/jsp03/survey_servlet/insert.do">
		<input type="radio" name="num" value="1">${dto.ans1 }<br>
		<input type="radio" name="num" value="2">${dto.ans2 }<br>
		<input type="radio" name="num" value="3">${dto.ans3 }<br>
		<input type="radio" name="num" value="4">${dto.ans4 }<br>
		<input type="hidden" name="survey_idx" value="${dto.survey_idx }">
		<input type="submit" value="OK">
	</form>
</body>
</html>
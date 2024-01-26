<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>survey summary</title>
</head>
<body>
	<h2>Summary</h2>
	<table border="1">
		<tr align="center">
			<th>num</th>
			<th>count</th>
			<th>rate</th>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.num }</td>
				<td>${dto.sum_num }</td>
				<td>${dto.rate }%</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/jsp03/survey_servlet/input.do">Home</a>
</body>
</html>
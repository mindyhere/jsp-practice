<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" width="700px">
		<c:forEach var="row" items="${list }">
			<tr>
				<td>${row.writer } 
				(<fmt:formatDate value="${row.reg_date }" pattern="yyyy-MM-dd HH:mm:ss" />)<br> 
				${row.contents }
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
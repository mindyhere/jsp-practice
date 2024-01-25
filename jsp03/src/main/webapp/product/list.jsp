<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>product list</title>
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	
</script>
</head>
<body>
	<h2>상품목록</h2>
	<c:forEach var="row" items="${items }">
	${row.PRODUCT_CODE }/ ${row.PRODUCT_NAME}/${row.PRICE }/${row.AMOUNT }<br>
	</c:forEach>
</body>
</html>
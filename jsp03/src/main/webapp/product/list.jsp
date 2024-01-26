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
	function product_edit(product_code) {
		location.href = "/jsp03/product_servlet/detail.do?product_code="
				+ product_code;
	}
</script>
</head>
<body>
	<h2>상품목록</h2>
	<!-- 링크연결 방식	"이동할 주소" 		  텍스트 -->
	<a href="/jsp03/product/form.jsp">상품추가</a>
	<!-- 버튼클릭 방식 				텍스트   클릭 → 링크 location.href='이동할 주소' -->
	<input type="button" value="상품추가"
		onclick="location.href='/jsp03/product/form.jsp'">
	<p>
	<table border="1">
		<tr>
			<th>상품코드</th>
			<th>이름</th>
			<th>가격</th>
			<th>수량</th>
			<th>비고</th>
		</tr>
		<c:forEach var="row" items="${items }">
			<tr>
				<td>${row.product_code}</td>
				<td>${row.product_name}</td>
				<td>${row.price}</td>
				<td>${row.amount}</td>
				<td><input type="button" value="편집"
					onclick="product_edit('${row.product_code}')"></td>
			</tr>
<%-- 			<tr>
				<td>${row.PRODUCT_CODE}</td>
				<td>${row.PRODUCT_NAME}</td>
				<td>${row.PRICE}</td>
				<td>${row.AMOUNT}</td>
				<td><input type="button" value="편집"
					onclick="product_edit('${row.PRODUCT_CODE}')"></td>
			</tr> --%>
		</c:forEach>
	</table>
</body>
</html>
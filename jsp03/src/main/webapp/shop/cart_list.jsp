<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	$(function () {
		$("#btnList").click(function () {
			location.href="/jsp03/product_servlet/list.do";
		});
		
		$("#btnDelete").click(function () {
			if(confirm("장바구니를 비우시겠습니까?")){
				location.href="jsp03/cart_servlet/delete_all.do";
			}
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<form name="form1" method="post" action="/jsp03/cart_servlet/update.do">
<table border="1" width="400px">
	<tr>
		<th>상품명</th>
		<th>단가</th>
		<th>수량</th>
		<th>금액</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach var="row" items="${list }">
		<tr>
			<td>${row.product_name }</td>
			<td>${row.price }</td>
			<td>
				<input type="number" style="width:50px;" min="0" max="100" name="amount" value="${row.amount }">
				<input type="hidden" name="product_code" value="${row.product_code }">
				<input type="hidden" name="cart_id" value="${row.cart_id }">
			</td>
			<td>${row.money }</td>
			<td><a href="/jsp03/cart_servlet/delete.do?cart_id=${row.cart_id }">삭제</a></td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<c:if test="${map.sum_money > 0 }">
				 	장바구니 금액 합계 : <fmt:formatNumber value="${map.sum_money }" pattern="#,###,###"/><br>
				 	배송료 : ${map.fee }<br>
				 	총합계 : <fmt:formatNumber value="${map.sum }" pattern="#,###,###"/>
				</c:if>
				<c:if test="${map.sum_money == 0 }">장바구니가 비었습니다.</c:if>
			</td>
		</tr>
</table>
<c:if test="${map.sum_money > 0 }">
	<button id="btnUpdate">수정</button>
</c:if>
</form>
<c:if test="${map.sum_money > 0 }">
	<button type="button" id="btnDelete">장바구니 비우기</button>
</c:if>
<button type="button" id="btnList">상품목록</button>
</body>
</html>
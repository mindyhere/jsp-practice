<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>상품정보</h2>
<table>
	<tr>
		<td>
			<img src="/jsp03/images/${dto.filename }" width="30%" height="30%">
		</td>
		<td align="ceneter">
			<table>
				<tr>
					<td>상품명</td>
					<td>${dto.product_name }</td>
				</tr>
				<tr>
					<td>가격</td>
					<td>${dto.price}</td>
				</tr>
				<tr>
					<td>상품설명</td>
					<td>${dto.description}</td>
				</tr>
				<tr>
					<td colspan="2">
						<form name="form1" method="post" action="/jsp03/cart_servlet/insert.do">
							<input type="hidden" name="product_code" value="${dto.product_code }">
							<select name="amount">
								<c:forEach begin="1" end="10" var="i">
									<option value="${i }">${i }</option>
								</c:forEach>
							</select>&nbsp;개
							<input type="submit" value="장바구니에 담기"> 
						</form>
					</td>
				</tr>
			</table>
		</td>	
	</tr>
</table>
</body>
</html>
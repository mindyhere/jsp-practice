<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>product detail</title>
<script>
	function del(product_code) {
		if (confirm("삭제할까요?")) {
			location.href = "/jsp03/product_servlet/delete.do?product_code="
					+ product_code;
		}
	}
</script>
</head>
<body>
	<h2>상품정보</h2>
	<form name="form1" method="post"
		action="/jsp03/product_servlet/update.do?product_code=${map.PRODUCT_CODE }">
		이름 : <input name="product_name" value=${map.PRODUCT_NAME }><br>
		가격 : <input type="number" name="price" value=${map.PRICE }><br>
		수량 : <input type="number" name="amount" value=${map.AMOUNT }><br>
		<input type="submit" value="수정"> 
		<input type="button"
			value="삭제" onclick="del('${map.PRODUCT_CODE}')">
	</form>
</body>
</html>
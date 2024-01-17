<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	int price = Integer.valueOf(request.getParameter("price"));
	int amount = Integer.valueOf(request.getParameter("amount"));
	/* 
	Integer.valueOf → 결과값을 기본자료형 int type으로 반환
	Integer.parseInt() → 결과값을 참조자료형 Integer 래퍼객체로 반환
	*/
	int money = price * amount;
	%>

	price : <%=price%><br> 
	amount : <%=amount%><br> 
	money : <%=money%>
</body>
</html>
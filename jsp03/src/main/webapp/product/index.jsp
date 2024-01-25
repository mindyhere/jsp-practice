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
// 서블릿 호출 (/product_servlet/list.do)
response.sendRedirect("/jsp03/product_servlet/list.do"); 
%>
</body>
</html>
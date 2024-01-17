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
	String str1 = "hello jsp";
	String str2 = "안녕하세요 :)";
	String str3 = "hola";
	System.out.println(str1);	//콘솔에 출력
	%>

	<h2>
		message:
		<%=str1%>
		<!-- % out.println(str1) %  -->
		<!-- 표현식 expression → 웹 브라우저에 출력  -->
	</h2>
	<h2>
		message:
		<%=str2%>
	</h2>
	<h2>
		message:
		<%=str3%>
	</h2>
</body>
</html>
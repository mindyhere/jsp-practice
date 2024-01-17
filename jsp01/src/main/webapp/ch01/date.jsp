<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- jsp page에서의 import 방법 → Java import문 작성(클래스경로) -->
<!-- 1행에 1개 클래스를 import -->
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Date now = new Date();
	out.println(now + "<br>"); //브라우저에 출력
	System.out.println(now + "<br>"); //콘솔에 출력(디버깅)
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a HH:mm:ss"); 
	String str_date = sdf.format(now);
	//날짜 → 문자열(스트링) 형식
	%>

	현재 날짜는
	<%=str_date%>입니다.
</body>
</html>
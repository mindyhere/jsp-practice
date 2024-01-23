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
	String id = "kim@naver.com";
	String passwd = "1234";
	int age = 20;
	double height = 170.5;
	
	//사용자 ↔ request.setAttribute() 서버요청
	//session.setAttribute("세션변수명", 세션값) → 세션 생성 및 수정
	session.setAttribute("id", id);
	session.setAttribute("passwd", passwd);
	session.setAttribute("age", age);
	session.setAttribute("height", height);
	out.println("저장되었습니다.");
	%>
	<a href="view_session.jsp">세션확인</a>
</body>
</html>
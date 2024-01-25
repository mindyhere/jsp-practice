<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	request.setAttribute() → 다음 페이지에서만 사용가능
	session.setAttribute() → 모든 페이지에서 사용가능. 사용자단위(로그인~로그아웃)
 -->
	<%
	session.setAttribute("name", "김철수");
	session.setAttribute("age", 20);
	session.setAttribute("job", "DBA");
	%>
	세션변수가 생성되었습니다.<br>
	<a href="ex03_result.jsp">확인</a>
</body>
</html>
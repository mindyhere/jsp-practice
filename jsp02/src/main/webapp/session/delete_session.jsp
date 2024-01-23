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
	//session.removeAttribute("세션변수명") → 개별 삭제
	//session.removeAttribute("passwd");
	//session.setAttribute("세션변수명", 세션값)
	session.invalidate();	//전체 삭제(세션 무효화)
	%>
	세션이 초기화되었습니다.
	<a href="view_session.jsp">세션확인</a>
</body>
</html>
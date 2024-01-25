<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2.7. ex05</title>
</head>
<body>
	<%@ page import="member.MemberDTO"%>
	<%
	MemberDTO dto = new MemberDTO();
	dto.setUserid("kim");
	dto.setName("김철수");
	dto.setPasswd("1234");
	request.setAttribute("dto", dto);	// 저장
	%>
	<!-- 다음페이지로 이동, 출력 -->
	<jsp:forward page="ex05_result.jsp" />
</body>
</html>
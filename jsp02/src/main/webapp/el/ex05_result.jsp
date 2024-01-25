<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2.8. ex05_result</title>
</head>
<body>
	<%@page import="member.MemberDTO"%>
	<%
	MemberDTO dto = (MemberDTO) request.getAttribute("dto");

	if (dto != null) {
		//null 체크
		out.println("이름: " + dto.getName() + "<br>");
		out.println("아이디: " + dto.getUserid() + "<br>");
		out.println("비번: " + dto.getPasswd() + "<br>");
	} else {
		out.println("출력할 값이 없습니다.");
	}
	%>

	<!-- el → 값이 null 이어도 예외가 발생하지 않음-->
	<!--dto.getName() ↔ ${dto.name} -->
	<!-- ${객체.변수명}	 -->
	<br> 이름 : ${dto.name }
	<br> 아이디 : ${dto.userid }
	<br> 비번 : ${dto.passwd }
</body>
</html>
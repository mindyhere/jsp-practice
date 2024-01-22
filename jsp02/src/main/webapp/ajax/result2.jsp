<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result2</title>
</head>
<body>
	<%@ page import="ajax.*"%>
	<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = new MemberDTO();
	dto.setUserid(id);
	dto.setPasswd(pwd);
	String result = dao.login(dto);

	if (result != null) {
		out.println(result + " 님 환영합니다.");
	} else {
		out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
	}
	%>
</body>
</html>
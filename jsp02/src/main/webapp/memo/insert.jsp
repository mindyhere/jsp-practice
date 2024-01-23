<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="memo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" context="text/html; charset=UTF-8">
<title>Insert ex.</title>
</head>
<body>
	<%
	String writer = request.getParameter("writer");
	String memo = request.getParameter("memo");
	
	MemoDTO dto = new MemoDTO();
	dto.setWriter(writer);
	dto.setMemo(memo);
	dto.setIp(request.getRemoteAddr()); // 클라이언트(사용자) ip 주소
	
	MemoDAO dao = new MemoDAO();
	dao.insert_memo(dto);
	
	response.sendRedirect("list.jsp");
	%>
</body>
</html>
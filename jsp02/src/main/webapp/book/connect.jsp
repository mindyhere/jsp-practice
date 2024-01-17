<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Connection conn = null;
	//DB접속
	try {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		//연결문자열 → jdbc:oracle:thin:@호스트:포트/데이터베이스

		//오라클계정 아이디, 비번
		String id = "java";
		String pass = "java1234";
		Class.forName("oracle.jdbc.driver.OracleDriver"); //오라클접속 클래스
		
		conn = DriverManager.getConnection(url, id, pass);
		out.println("오라클에 접속되었습니다.");
	} catch (Exception e) {
		out.println("오라클 접속에러...");
		e.printStackTrace();
	}
	%>
</body>
</html>
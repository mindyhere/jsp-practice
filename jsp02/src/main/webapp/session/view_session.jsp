<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.util.Enumeration"%>
	<%
	Enumeration<String> en = session.getAttributeNames();
	// 집합(List, Map)					모든 변수들

	while (en.hasMoreElements()) {
		// 다음 요소가 있으면 true
		String key = en.nextElement();
		// session.getAttribute("세션변수명") → 세션 읽기
		Object value = session.getAttribute(key);
		out.println("key: " + key + ",");
		out.println("value: " + value + "<br>");
	}

	String id = (String) session.getAttribute("id");
	String passwd = (String) session.getAttribute("passwd");
	int age = 0;
	if (session.getAttribute("age") != null) {
		age = (int) session.getAttribute("age");
	}
	double height = 0;
	if (session.getAttribute("height") != null) {
		height = (double) session.getAttribute("height");
	}
	%>
	아이디:<%=id%><br> 
	비번:<%=passwd%><br> 
	나이:<%=age%><br> 
	키:<%=height%><br>
	<a href="delete_session.jsp">세션삭제</a>
</body>
</html>
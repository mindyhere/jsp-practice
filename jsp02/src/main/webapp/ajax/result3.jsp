<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>keyword result</title>
</head>
<body>
	<%@ page import="keyword.KeywordDAO"%>
	<%@ page import="java.util.*"%>
	<%
	KeywordDAO dao = new KeywordDAO();
	String keyword = request.getParameter("search");
	List<String> items = dao.list(keyword);

	for (String str : items) {
		out.println(str + "<br>");
	}
	%>
</body>
</html>
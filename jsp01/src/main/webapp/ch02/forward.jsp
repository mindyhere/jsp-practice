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
	/* request.setAttribute("id", "kim");
	내장객체(요청사항)			변수		값 */
	request.setAttribute("id", "kim");
	request.setAttribute("name", "김철수");
	/* RequestDispatcher rd = request.getRequestDispatcher("forward_result.jsp");
	rd.forward(request, response); */
	%>
	<jsp:forward page="forward_result.jsp" />
</body>
</html>
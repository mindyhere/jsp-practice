<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2.2. ex02.</title>
</head>
<body>
	<form method="post">
		이름: <input name="name" value="<%=request.getParameter("name")%>">
		나이: <input name="age" value="<%=request.getParameter("age")%>">
		<input type="submit" value="확인">
	</form>
	이름 : <%=request.getParameter("name")%>, 나이 :<%=request.getParameter("age")%>
	<hr>
	<form method="post">

		이름: <input name="name" value="${param.name }">
		<!-- ${param.name } → request.getParameter("name")	 -->
		나이: <input name="age" value="${param.age }"> 
		<input type="submit" value="확인">
	</form>
	이름: ${param.name }, 나이: ${param.age }
</body>
</html>
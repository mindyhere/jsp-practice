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
	String name = request.getParameter("name");
	int kor = Integer.parseInt(request.getParameter("kor"));
	int eng = Integer.parseInt(request.getParameter("eng"));
	int mat = Integer.parseInt(request.getParameter("mat"));
	int total = kor + eng + mat;
	double average = total / 3.0;
	%>

	이름:
	<%=name%><br> 국어:
	<%=kor%><br> 영어:
	<%=eng%><br> 수학:
	<%=mat%><br> 총점:
	<%=total%><br> 평균:
	<%=String.format("%.1f", average)%><br>
</body>
</html>
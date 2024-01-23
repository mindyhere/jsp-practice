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
	Integer count = (Integer) session.getAttribute("counter");
	//						   세션변수			      변수명
	int num = 1;

	if (count == null) { // 첫방문
		count = 1;
	} else { // 두번째 방문 이후
		num = count.intValue();	// 클래스 → 숫자
		num++;
		count = num;
	}

	session.setAttribute("counter", count);	//세션에 저장
	%>
	<%=count.intValue()%>번째 방문입니다.
</body>
</html>
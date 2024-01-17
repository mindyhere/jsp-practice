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
	String[] fruits = request.getParameterValues("fruits");
	/* request.getParamete("변수") 1개
	getParameterValues("배열변수명") 여러개
	*/
	if (fruits != null) {
		//체크박스를 선택했을 때
		for (int i = 0; i < fruits.length; i++) {
			out.println(fruits[i] + "<br>");
		}
	} else {
		//체크박스를 선택하지 않았을 때
		out.println("선택된 값이 없습니다.");
	}
	%>

</body>
</html>
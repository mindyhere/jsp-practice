<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function winclose() {
		opener.document.form1.num.value = document.form1.num.value;
		/*opener → 팝업창을 오픈한 메인 window의 주소    현재창(팝업창)*/
		window.close();
		/*현재 창 닫기*/
	}
</script>
</head>
<body>
	<%
int num=Integer.parseInt(request.getParameter("num"));
%>

	<h2>
		Table
		<%=num %></h2>
	<%
for(int i=1;i<=9;i++){
	out.println(num+" X "+i+ " = "+num*i+"<br>");
}
%>
	<form name="form1">
		메인창에 전달할 값을 입력하세요. <br>
		<input type="text" name="num"> 
		<input type="button" value="닫기" onclick="winclose()">
	</form>
</body>
</html>
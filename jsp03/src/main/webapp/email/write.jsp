<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/jsp03/email_servlet/send.do"\>
	발신자 이름: <input name="sender_name" value="admin" readonly><br>
	<!-- 발신자 이메일: <input name="sender_mail"><br>  -->
	수신자 이메일: <input name="receive_mail"><br> 
	제목: <input name="subject"><br>
	내용: <br><textarea rows="5" cols="80" name="message"></textarea><br>
	<input type="submit" value="전송">
</form>

<c:if test="${param.message == 'ok' }">
	<span style="color: blue">이메일 발송이 완료되었습니다.</span>
</c:if>
<c:if test="${param.message == 'error' }">
	<span style="color: red">이메일 발송 실패</span>
</c:if>
</body>
</html>
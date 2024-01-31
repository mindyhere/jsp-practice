<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 수정/삭제</title>
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	function gb_update() {
		let form1 = $("#form1");
		let name = $("#name");
		let email = $("#email");
		let passwd = $("#passwd");
		let contents = $("#contents");
		
		if(name.val()==""){
			alert("이름을 입력하세요.");
			name.focus();
			return;
		}
		if(email.val()==""){
			alert("이메일 입력하세요.");
			email.focus();
			return;
		}
		if(passwd.val()==""){
			alert("비밀번호를 입력하세요.");
			passwd.focus();
			return;
		}
		if(contents.val()==""){
			alert("내용을 입력하세요.");
			contents.focus();
			return;
		}
		document.form1.action = "/jsp03/gb_servlet/update.do";
		document.form1.submit();
	}
	
	function gb_delete() {
		let form1 = $("#form1");
		if(confirm("삭제하시겠습니까?")){
			document.form1.action = "/jsp03/gb_servlet/delete.do";
			document.form1.submit();
		}
	}
</script>
</head>
<body>
<h2>방명록 수정/삭제</h2>
<form name="form1" id="form1" method="post">
	<table border="1" style="width: 500px">
		<tr>
			<td style="text-align: center; background-color:cyan">이름</td>
			<td><input type="text" name="name" id="name" size="40" value="${dto.name }"></td>
		</tr>
		<tr>
			<td style="text-align: center; background-color:cyan">이메일</td>
			<td><input type="text" name="email" id="email" size="40" value="${dto.email }"></td>
		</tr>
		<tr>
			<td style="text-align: center; background-color:cyan">비밀번호</td>
			<td><input type="password" name="passwd" id="passwd" size="40" value="${dto.passwd}"></td>
		</tr>
		<tr  align="center">
			<td colspan="2"><textarea rows="5" cols="55" name="contents" id="contents">${dto.contents }</textarea></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="hidden" name="idx" value="${dto.idx }">
				<input type="button" value="수정" onclick="gb_update()"> 
				<input type="button" value="삭제" onclick="gb_delete()"> 
				<input type="button" value="목록" onclick="location.href='/jsp03/gb_servlet/list.do';"> 
			</td>
		</tr>
	</table>
</form>
	
</body>
</html>
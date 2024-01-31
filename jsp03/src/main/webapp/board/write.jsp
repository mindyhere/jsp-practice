<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script src="/jsp03/include/jquery-3.7.1.min.js"></script>
<script>
	$(function() {
		$("#btnSave").click(function() {
			let writer = $("#writer").val();
			let subject = $("#subject").val();
			let contents = $("#contents").val();
			let passwd = $("#passwd").val();

			if (writer == "") {
				alert("이름을 입력하세요.");
				$("#writer").focus();	// 커서 이동, 입력가능한 상태
				return;	// 함수종료+값 전달
			}
			if (subject == "") {
				alert("제목을 입력하세요.");
				$("#subject").focus();
				return;
			}
			if (contents == "") {
				alert("내용을 입력하세요.");
				$("#contents").focus();
				return;
			}
			if (passwd == "") {
				alert("비밀번호를 입력하세요.");
				$("#passwd").focus();
				return;
			}
			
			// 태그id로 접근 → document.getElementById("태그id")  또는  $("#태그id") 
			// 태그name으로 접근 
			let filename = document.form1.file1.value;	
			let start = filename.lastIndexOf(".") + 1; // 파일 확장자를 검사(없으면 index = -1)
			
			if (start != -1) {	
				let ext = filename.substring(start, filename.length);	// substring(시작, 끝) → 확장자명	
				
				if (ext == "jsp" || ext == "exe") {
					alert("업로드할 수 없는 파일입니다.");
					return;
				}
			}
			document.form1.submit();
		});
	});
</script>
</head>
<body>
	<h2>글쓰기</h2>
	<form name="form1" method="post"
		action="/jsp03/board_servlet/insert.do" enctype="multipart/form-data" >
		<table border="1" width="700px">
			<tr>
				<td align="center">이름</td>
				<td><input name="writer" id="writer"></td>
			</tr>
			<tr>
				<td align="center">제목</td>
				<td><input name="subject" id="subject" size="60"></td>
			</tr>
			<tr>
				<td align="center">본문</td>
				<td><textarea rows="5" cols="60" name="contents" id="contents"></textarea></td>
			</tr>
			<tr>
				<td align="center">첨부파일</td>
				<td><input type="file"  name="file1"></td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td><input type="password" name="passwd" id="passwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="확인" id="btnSave"></td>
			</tr>
		</table>
	</form>
</body>
</html>
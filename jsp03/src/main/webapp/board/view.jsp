<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/jsp03/include/jquery-3.7.1.min.js"></script>
<script>
	$(function() {
		$("#btnEdit").click(function() {
			document.form1.action = "/jsp03/board_servlet/check_pwd.do";
			document.form1.submit();
		});

		$("#btnList").click(function() {
			location.href = "/jsp03/board_servlet/list.do";
		});

		// 댓글추가
		list_comment();
		$("#btnSave").click(function() {
			insert_comment();
		});
		
		// 답변글 추가
		$("#btnReply").click(function() {
			document.form1.action = "/jsp03/board_servlet/input_reply.do";
			document.form1.submit();
		});
	});
	
	function insert_comment() {
		let params={"board_num":${dto.num }, "writer":$("#writer").val(), "contents":$("#contents").val()};
		
		$.ajax({
			type:"post",
			url:"/jsp03/board_servlet/insert_comment.do",
			data: params,
			success: function() {
				// 답글 추가(성공) 완료 → 입력란 초기화
				$("#writer").val("");
				$("#contents").val("");
				list_comment();
			}
		});
	}
	
	function list_comment() {
		$.ajax({
			url: "/jsp03/board_servlet/list_comment.do",
			data: {"num": ${dto.num}},
			success: function(txt) {
				console.log("===div 출력확인===\n"+txt);	// 콜백한 결과 출력
				$("#div_comment").html(txt);
			}
		});
	}
</script>
</head>
<body>
	<form name="form1" method="post">
		<table border="1" width="700px">
			<tr>
				<td width="10%" align="center" style="background-color: orange">날짜</td>
				<td width="40%">${ dto.reg_date }</td>
				<td width="10%" style="background-color: orange">조회수</td>
				<td width="40%">${ dto.hit }</td>
			</tr>
			<tr>
				<td align="center" style="background-color: orange">이름</td>
				<td colspan="3">${dto.writer }</td>
			</tr>
			<tr>
				<td align="center" style="background-color: orange">제목</td>
				<td colspan="3">${dto.subject}</td>
			</tr>
			<tr>
				<td align="center" style="background-color: orange">본문</td>
				<td colspan="3">${dto.contents }</td>
			</tr>
			<tr>
				<td align="center" style="background-color: orange">비밀번호</td>
				<td colspan="3"><input type="password" name="passwd" id="passwd"> 
					<c:if test="${param.message=='error' }">
						<span style="color: red">비밀번호가 일치하지 않습니다.</span>
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="center" style="background-color: orange">첨부파일</td>
				<td colspan="3">
					<c:if test="${dto.filesize >0 }">
						${dto.filename } (${dto.filesize } bytes)
						<a href="/jsp03/board_servlet/download.do?num=${dto.num }">[다운로드]</a>
					</c:if></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="hidden" name="num" value="${dto.num }"> 
					<input type="button" value="수정/삭제" id="btnEdit"> 
					<input type="button" value="답변" id="btnReply">
					<input type="button" value="목록" id="btnList">
				</td>
			</tr>
		</table>
	</form>
	
	<table border="0" width="700px">
		<tr>
			<td><input id="writer" placeholder="이름"></td>
			<td rowspan="2">
				<button id="btnSave" type="button">확인</button>
			</td>
		</tr>
		<tr>
			<td>
				<textarea rows="5" cols="80" placeholder="내용" id="contents"></textarea>
			</td>
		</tr>
	</table>
	<div id="div_comment"></div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page index</title>
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	// $(document).ready(function(){}); → 축약 $(function(){});  	//페이지 로딩 완료 후 자동실행
	$(function() {
		list('1');
	});

	function list(cur_page) {
		let param = {"cur_page" : cur_page};
		// 변수			 key		value
		
		// 백그라운드 실행
		$.ajax({
			url : "/jsp03/page_servlet/list.do",
			data : param,	// 전달값
			
			// txt: 응답텍스트 response text → 함수실행
			success : function(txt) {
				$("#result").html(txt);
			}
		});
	}
</script>
</head>
<body>
	<h2>페이지 나누기</h2>
	<div id="result"></div>
</body>
</html>
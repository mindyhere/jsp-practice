<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memo</title>
<link rel="stylesheet" href="/jsp03/include/css/bootstrap.css">
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="/jsp03/include/js/bootstrap.js"></script>
<script>
	$(function () {
		list();
		
		$("#btnSave").click(function() {
			insert();
		});
		
		$("#btnSearch").click(function() {
			list();
		});
	});
	
	function list() {
		let params={"search_option":$("#search_option").val(),"search":$("#search").val()};
		//let params={"searchkey":$("#searchkey").val(),"search":$("#search").val()};
		
		$.ajax({
			url:"/jsp03/memo_servlet/list.do",
			data:params,
			success:function(txt){
				$("#result").html(txt);
			}
		});
	}
	
	function insert() {
		let writer=$("#writer").val();
		let memo=$("#memo").val();
		let params={"writer": writer, "memo":memo};
		
		$.ajax({
			type:"post",
			url:"/jsp03/memo_servlet/insert.do",
			data:params,
			success:function(){
				list();
				$("#writer").val("");
				$("#memo").val("");
			}
		});
	}
</script>
</head>

<body>
<h2 class="text-center">한줄 메모장</h2>
<div class="col-sm-2">
	<input id="writer" class="form-control" placeholder="이름">
</div>
<div class="col-sm-4">
	<input id="memo" class="form-control" placeholder="메모">
	<input type="button" id="btnSave" value="확인" class="btn btn-primary">
</div>
<hr>
<div class="col-sm-2">
	<select id="search_option" class="form-control">
		<option value="writer">이름</option>
		<option value="memo">메모</option>
		<option value="writer_memo">이름+메모</option>
	</select>
</div>
<div class="col-sm-4">
	<input id="search" class="form-control" value="${search }">
	<input type="button" id="btnSearch" value="조회" class="btn btn-info">
</div>
<div id="result"></div>
</body>
</html>
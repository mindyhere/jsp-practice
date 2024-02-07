<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memo</title>
</head>
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	$(function () {
		list();
		
		$("#btnSave").click(function () {
			insert();
		});
		
		$("#btnSearch").click(function () {
			list();
		});
	});
	
	function list() {
		let params="searchkey="+$("#searchkey").val()+"&search="+$("#search").val();
		
		$.ajax({
			url:"/jsp04/memo_servlet/list.do",
			data:params,
			success:function(txt){
				console.log("txt: "+txt);
				$("#result").html(txt);
			}
		});
	}
	
	function insert() {
		let writer=$("#writer").val();
		let memo=$("#memo").val();
		let params="writer=" + writer + "&memo="+memo;
		
		$.ajax({
			type:"post",
			url:"/jsp04/memo_servlet/insert.do",
			data:params,
			success:function(){
				list("search=");
				$("#writer").val("");
				$("#memo").val("");
			}
		});
	}
</script>
<body>
	이름 : <input id="writer" size="10">
	<br> 
	메모 : <input id="memo" size="40">
	<input type="button" id="btnSave" value="확인">
	<br>
	<select id="searchkey">
		<option value="writer">이름</option>
		<option value="memo">메모</option>
		<option value="writer_memo">이름+메모</option>
	</select>
	<input type="text" id="search" value="${search }">
	<input type="button" id="btnSearch" value="조회">
	<div id="result"></div>
</body>
</html>
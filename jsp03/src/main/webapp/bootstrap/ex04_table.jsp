<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jsp03/include/css/bootstrap.css">
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="/jsp03/include/js/bootstrap.js"></script>
</head>
<body>
	<table class="table">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>나이</td>
			<td>주소</td>
			<td>전화번호</td>
		</tr>
		<tr>
			<td>1</td>
			<td>kim</td>
			<td>20</td>
			<td>서울시</td>
			<td>010-111-5555</td>
		</tr>
		<tr>
			<td>2</td>
			<td>park</td>
			<td>30</td>
			<td>인천시</td>
			<td>010-222-5555</td>
		</tr>
		<tr>
			<td>3</td>
			<td>lee</td>
			<td>40</td>
			<td>대전시</td>
			<td>010-555-5555</td>
		</tr>
	</table>

	<div class="container">
		<table class="table table-striped">
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>나이</td>
				<td>주소</td>
				<td>전화번호</td>
			</tr>
			<tr>
				<td>1</td>
				<td>kim</td>
				<td>20</td>
				<td>서울시</td>
				<td>010-111-5555</td>
			</tr>
			<tr>
				<td>2</td>
				<td>park</td>
				<td>30</td>
				<td>인천시</td>
				<td>010-222-5555</td>
			</tr>
			<tr>
				<td>3</td>
				<td>lee</td>
				<td>40</td>
				<td>대전시</td>
				<td>010-555-5555</td>
			</tr>
		</table>
	</div>
	
	<div class="container">
		<table class="table table-hover">
			<tr>
				<td>번호</td>
				<td>이름</td>
				<td>나이</td>
				<td>주소</td>
				<td>전화번호</td>
			</tr>
			<tr>
				<td>1</td>
				<td>kim</td>
				<td>20</td>
				<td>서울시</td>
				<td>010-111-5555</td>
			</tr>
			<tr>
				<td>2</td>
				<td>park</td>
				<td>30</td>
				<td>인천시</td>
				<td>010-222-5555</td>
			</tr>
			<tr>
				<td>3</td>
				<td>lee</td>
				<td>40</td>
				<td>대전시</td>
				<td>010-555-5555</td>
			</tr>
		</table>
		<hr>
		<a class="btn btn-primary">글쓰기</a>
		<div class="text-center">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
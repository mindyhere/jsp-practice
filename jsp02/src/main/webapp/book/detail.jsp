<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서정보</title>
<script>
	function del(idx) {
		if (confirm("삭제할까요?")) {
			//확인 true, 취소 false
			
			location.href = "/jsp02/book_servlet/delete.do?idx=" + idx;
			//주소이동
		}
	}
</script>

</head>
<body>
	<%@ page import="book.BookDTO"%>
	<h2>도서 정보</h2>
	<%
	BookDTO dto = (BookDTO) request.getAttribute("dto");
	%>

	<form action="/jsp02/book_servlet/update.do" method="post">
		<!-- <input type="hidden" name="idx" value="<%=dto.getIdx()%>"><br>
		input type="hidden" 필드숨김. 화면에서 표시되지 않는 값 -->

		도서번호: <input name="idx" value="<%=dto.getIdx()%>" readonly><br>
		<!-- readonly 읽기전용. 수정불가 -->
		도서명: <input name="title" value="<%=dto.getTitle()%>"><br>
		저자: <input name="author" value="<%=dto.getAuthor()%>"><br>
		가격:<input name="price" value="<%=dto.getPrice()%>"><br>
		수량:<input name="amount" value="<%=dto.getAmount()%>"><br>
		
		<input type="submit" value="수정"> 
		<input type="button" value="삭제" onclick="del('<%=dto.getIdx()%>')">
		<!-- 삭제버튼 추가 -->

	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="book.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book list ex.</title>
</head>
<body>
	<%
	/*리스트 가져오기: 모델1(jsp위주), 모델2(MVC패턴) 두가지 방법
	Model → 데이터 저장/처리(dao, dto 클래스)
	View → 화면 출력(jsp)
	Controller → 흐름 제어(servlet)*/
	
	//BookDAO dao = new BookDAO();
	//List<BookDTO> items = dao.list_book();
	List<BookDTO> items = (List)request.getAttribute("items");	//모델2 활용
	%>
	<a href="/jsp02/book/book.html">도서 등록</a>
	<table border="1">
		<tr>
		<!-- th 테이블헤더: 가운데정렬, 볼드체 적용 -->
			<th>idx</th>
			<th>title</th>
			<th>author</th>
			<th>price</th>
			<th>amount</th>
		</tr>
		<%
		for (BookDTO dto : items) {
		%>
		<tr>
		<!-- %=변수% ↔ %out.println(변수);% 동일함 -->
			<td><%=dto.getIdx()%></td>
			<td><%=dto.getTitle()%></td>
			<td><%=dto.getAuthor()%></td>
			<td><%=dto.getPrice()%></td>
			<td><%=dto.getAmount()%></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>4.12. list</title>
<!-- <script src="http://code.jquery.com/jquery-3.7.1.min.js"></script> -->
<script src="/jsp03/include/jquery-3.7.1.min.js"></script>
<script>
	$(function() { // 페이지 로드 완료 후 자동실행
		$("#btnWrite").click(function() {
			location.href = "/jsp03/board/write.jsp"; // 실행주소
		});
	});
</script>
</head>
<body>
	<h2>게시판</h2>
	<button type="button" id="btnWrite">글쓰기</button>
	<table border="1">
		<tr style="background-color: orange;">
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>첨부파일</th>
			<th>다운로드</th>
		</tr>

		<!-- JSTL 활용 : 개별		  전체 -->
		<c:forEach var="dto" items="${list }">
			<tr align="center">
				<td>${dto.num }</td>
				<td>${dto.writer }</td>
				<td>${dto.subject }</td>
				<td>${dto.reg_date }</td>
				<td>${dto.hit }</td>
				<!--  <td>${dto.filename}</td> -->
				<!-- 첨부파일 유무 → 아이콘으로 처리 -->
				<td><c:if test="${dto.filesize >0 }">
						<a href="/jsp03/board_servlet/download.do?num=${dto.num }"> <img
							src="../images/file.png" align="center">
						</a>
					</c:if></td>
				<td>${dto.down }</td>
			</tr>
		</c:forEach>

		<%-- 기본 자바문법으로 작성
		<%@ page import="java.util.List" %>
		<%@ page import="board.BoardDTO" %>
		<%
		List<BoardDTO> list = (List<BoardDTO>) request.getAttribute("list");
		for (int i = 0; i < list.size(); i++) {
			// Board dto : list 
			BoardDTO dto = list.get(i);
		%>
		<tr>
			<td><%=dto.getNum()%></td>
			<td><%=dto.getWriter()%></td>
			<td><%=dto.getSubject()%></td>
			<td><%=dto.getReg_date()%></td>
			<td><%=dto.getHit()%></td>
			<td><%=dto.getFilename()%></td>
			<td><%=dto.getDown()%></td>
		</tr>
		<%
		}
		%> 
		--%>
	</table>
</body>
</html>
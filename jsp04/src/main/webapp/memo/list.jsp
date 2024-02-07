<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>5.8. list</title>
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	$(function() {
		$("#chkAll").click(function() {
			if ($("#chkAll").prop("checked")) {
				$("input[name=idx]").prop("checked", true);
			} else {
				$("input[name=idx]").prop("checked", false);
			}
		});

		$("#btnAllDel").click(function() {
			document.form1.action = "/jsp04/memo_servlet/delete_all.do";
			document.form1.submit();
		});
	});

	function memo_del(idx) {
		location.href = "/jsp04/memo_servlet/del.do?idx=" + idx;
	}
</script>
</head>
<body>
<c:choose>
	<c:when test="${list.size() == 0 }">
		등록된 메모가 없습니다.
	</c:when>
	<c:when test="${list.size() > 0 }">
		<form method="post" name="form1">
			<table border="1">
				<tr>
					<th><input type="checkbox" id="chkAll"></th>
					<th>번호</th>
					<th>이름</th>
					<th>메모</th>
					<th>날짜</th>
					<th><input type="button" value="선택삭제" id="btnAllDel"></th>
				</tr>
				<c:forEach var="row" items="${list }">
					<tr>
						<td><input type="checkbox" name="idx" value="${row.idx}"></td>
						<td>${row.idx}</td>
						<td>${row.writer }</td>
						<td><a href="/jsp04/memo_servlet/view.do?idx=${row.idx }">${row.memo }</a></td>
						<td>${row.post_date}</td>
						<td><input type="button" value="삭제" onclick="memo_del('${row.idx}')"></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</c:when>
</c:choose>
</body>
</html>
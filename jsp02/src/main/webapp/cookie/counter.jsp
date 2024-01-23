<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="common.MyCookie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String count = MyCookie.getCookie(request.getCookies(), "count");
	int int_count = 0;
	Date date = new Date();
	long now_time = date.getTime(); // 타임스탬프(1970.1.1~현재, 밀리초))
	String str_visit = MyCookie.getCookie(request.getCookies(), "visit-time"); // 최근 방문시간
	long visit_time = 0;

	if (str_visit != null && !str_visit.equals("")) {
		visit_time = Long.parseLong(str_visit);
	}

	if (count == null || count.equals("")) {
		response.addCookie(new Cookie("count", "1"));
		// 최초방문		쿠키 추가		   변수명		값
		response.addCookie(new Cookie("visit_time", Long.toString(now_time)));
		// 최근 방문시간
		int_count = 1;
	} else { // 두번째 방문이후
		long period = now_time - visit_time; // 현재시각 - 직전시각
		//out.println("now:" + now_time + "<br>");
		//out.println("previous:" + visit_time + "<br>");
		//out.println("difference:" + period + "<br>");
		int_count = Integer.parseInt(count);

		if (period > 3 * 1000) {
			int_count++;
			response.addCookie(new Cookie("count", Integer.toString(int_count)));
			response.addCookie(new Cookie("visit_time", Long.toString(now_time)));
		}
	}
	%>
	<%=int_count%>번째 방문입니다.
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://www.google.com/jsapi"></script> // 구글차트 호출
<script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	google.load("visualization", "1", {"packages" : [ "corechart" ]});
	google.setOnLoadCallback(drawChart); // 구글차트 라이브러리 로딩 완료
	
	function drawChart() {
		let json_data = $.ajax({
			url : "/jsp03/chart_servlet/cart_money_List.do",
			async : false // 비동기방식X
		}).responseText;
		console.log(json_data);
		
		let data = new google.visualization.DataTable(json_data); // json(차트데이트) → 차트생성
		let chart = new google.visualization.PieChart(document.getElementById("chart_div"));
		chart.draw(data, {
			title : "차트 예제",
			width : 600,
			height : 440
		});
	}
</script>
</head>
<body>
	<div id="chart_div"></div>
	<button type="button" onclick="drawChart()">refresh</button>
</body>
</html>
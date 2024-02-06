<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 구글차트 호출 -->
<script src="https://www.google.com/jsapi"></script>
<!-- <script src="http://code.jquery.com/jquery-3.7.1.min.js"></script> -->
<script src="../include/jquery-3.7.1.min.js"></script>
<script>
	google.load("visualization", "1", {
		"packages" : [ "corechart" ]
	
	/* sql ==> json{key:value, key:value}
	{} object, [] array 
	
	dao.cart_money()→getChartData()
	"cols": [
		{
			"label": "상품명",
			"type": "string"
		},
		{
			"label": "금액",
			"type": "number"
		}		
	*/
	
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		let json_data = $.ajax({
			url : "/jsp03/chart_servlet/cart_money_list.do",
			async : false
		}).responseText;
		let data = new google.visualization.DataTable(json_data);
		let chart = new google.visualization.PieChart(document
				.getElementById("chart_div"));
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
	<button id="btn" type="button" onclick="drawChart()">refresh</button>
</body>
</html>
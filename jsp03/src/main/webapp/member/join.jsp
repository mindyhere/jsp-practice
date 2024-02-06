<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 다음에서 제공하는 오픈API 활용 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function showPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						let fullAddr = "";
						let extraAddr = "";
						
						// == equal ↔ === :내용+자료형 비교
						// != not equal ↔ !==
						if (data.userSelectedType === "R") {
							// 도로명
							fullAddr = data.roadAddress;
						} else {
							// 지번
							fullAddr = data.jibunAddress;
						}
						if (data.userSelectedType === "R") {
							if (data.bname !== "") {
								extraAddr += data.bname;
							}
							if (data.buildingName !== "") {
								extraAddr += (extraAddr !== "" ? ","+ data.buildingName : data.buildingName);
							}
							fullAddr += (extraAddr !== "" ? "(" + extraAddr + ")" : "");
						}
						document.getElementById("post_code").value = data.zonecode;
						document.getElementById("address1").value = fullAddr;
						document.getElementById("address2").focus(); // 상세주소 입력란으로 포커스 이동
					}
				}).open();
	}
</script>
</head>
<body>
	<form name="form1" method="post">
		우편번호 : <input name="zipcode" id="post_code" readonly>
		<!-- readonly 속성 → 사용자 입력X, 값을 읽어오는 것만 가능하게 함 -->
		<input type="button" onclick="showPostcode()" value="우편번호찾기"><br>
		주소 : <input name="address1" id="address1" size="60"><br>
		상세주소 : <input name="address2" id="address2">
	</form>
</body>
</html>
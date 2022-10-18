<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON 테스트</title>
<script src="http://code.jquery.com/jquery-Latest.min.js"></script>
<script type="text/javascript">
	$(function () {
		let jsonData= '{"name" : "홍길동", "age" : 50, "job" : "도적"}';
		$("input").click(function () {
			let jsonInfo = JSON.parse(jsonData);
			let data = "<h2>회원정보</h2>"
			data += "====================<br>"
			data += "이름 : " + jsonInfo.name + "<br>"
			data += "나이 : " + jsonInfo.age + "<br>"
			data += "직업 : " + jsonInfo.job + "<br>"
			$("#output").html(data);
		});
	});
</script>
</head>
<body>
	<input type="button" value="JSON 데이터 출력">
	<div id="output"></div>
</body>
</html>
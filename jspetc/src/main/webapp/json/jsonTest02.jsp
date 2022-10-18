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
		let jsonData= '{"age" : [25, 40, 27]}';
		$("input").click(function () {
			let jsonInfo = JSON.parse(jsonData);
			let data = "<h2>회원나이</h2>"
			data += "====================<br>"
			for(let i in jsonInfo.age) {
				data += jsonInfo.age[i] + "<br>"
			}
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
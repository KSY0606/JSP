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
		let jsonData= '{"name" : ["김철수", "홍길동", "이영희"]}';
		$("input").click(function () {
			let jsonInfo = JSON.parse(jsonData);
			let data = "<h2>회원이름</h2>"
			data += "====================<br>"
			for(let i in jsonInfo.name) {
				data += jsonInfo.name[i] + "<br>"
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
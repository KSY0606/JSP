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
		let jsonData= '{"member" : [{"name" : "홍길동", "age" : 50, "job" : "도적"}' +
			',{"name" : "김철수", "age" : 33, "job" : "영업사원"}' +
			',{"name" : "이영희", "age" : 26, "job" : "사무원"}]}';
		$("input").click(function () {
			let jsonInfo = JSON.parse(jsonData);
			let data = "<h2>회원정보</h2>"
			data += "=============================<br>"
			for(let i in jsonInfo.member) {
			data += "이름 : " + jsonInfo.member[i].name + "<br>"
			data += "나이 : " + jsonInfo.member[i].age + "<br>"
			data += "직업 : " + jsonInfo.member[i].job + "<br>"
			data += "=============================<br>";
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
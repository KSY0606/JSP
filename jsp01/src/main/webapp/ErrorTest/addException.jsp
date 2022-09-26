<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%> <!-- 에러가 발생하면 예외처리 페이지로 지정. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
</head>
<body>
	<h2>에러 내용</h2>
	<h3><%=exception.toString()%></h3> <!-- exception 내장객체 -->
	<h3>숫자만 입력가능합니다. 다시 시도해보세요 => <a href="adder.html">다시하기</a></h3>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>               <%-- 예전 버전일 경우 기본값이 false가 아니므로 isELIgnored="false"를 작성해줘야 함 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>표현언어로 여러 형태의 데이터 출력</h2>
	<h2>숫자값=${500}<br>
		홍길동님${"안녕하세요"}<br>
		연산=>${20+50}<br>
		불=>${false}<br>
		실수값=>${5.3}<br>
		연산=>${"10"+1 }<br>
		<%-- 숫자문자=>${"철수"+20}
		문자연결=>${"철수"+"안녕"} --%>
	</h2>
</body>
</html>
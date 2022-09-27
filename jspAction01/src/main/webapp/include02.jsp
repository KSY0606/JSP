<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인클루드 태그</title>
</head>
<body>
	<h1>가나 쇼핑몰</h1>			 <!-- 동적으로 포워딩 한다는 의미 -->
	<jsp:include page="img_inc.jsp" flush="true">
		<jsp:param value="강아지풀" name="name"/>
		<jsp:param value="first.jpg" name="imgName"/>
	</jsp:include>
	<p>저희 쇼핑몰을 자주 찾아와 주세요</p>
</body>
</html>
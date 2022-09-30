<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>c:if 활용 예제</title>
</head>
<body>
	<c:if test="${empty param.id }">
		<h2>아이디를 입력해주세요</h2>
		<a href="login.jsp">로그인화면으로 이동</a>
	</c:if>
	<c:if test="${!empty param.id }">
		<h2>환영합니다. ${param.id}님!!</h2>
	</c:if>
</body>
</html>
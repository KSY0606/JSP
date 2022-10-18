<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<fmt:setLocale value="ko_KR"/>
<a href="main_ko.jsp">한국어</a><a href="main_en.jsp">영어</a>
	<fmt:bundle basename="resourse.member">
		<h1><fmt:message key="mem.title"/></h1>
		<h2><fmt:message key="mem.name"/></h2>
		<h2><fmt:message key="mem.address"/></h2>
		<h2><fmt:message key="mem.job"/></h2>
	</fmt:bundle>
</body>
</html>
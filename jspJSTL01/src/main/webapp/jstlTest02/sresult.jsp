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
	<title>c:choose 활용 예제</title>
</head>
<body>
	<%-- 풀이
	<c:set var="score" value="${param.score}"/>
	<h2>시험점수 : ${score}점</h2>
	<c:choose>
		<c:when test="${0 <= param.score && param.score <= 100}">
			<c:choose>
				<c:when test="${score >= 90}">
					<h2>결과 : A학점</h2>
				</c:when>
				<c:when test="${score >= 80}">
					<h2>결과 : B학점</h2>
				</c:when>
				<c:when test="${score >= 70}">
					<h2>결과 : C학점</h2>
				</c:when>
				<c:when test="${score >= 60}">
					<h2>결과 : D학점</h2>
				</c:when>
				<c:otherwise>
					<h2>결과 : F학점</h2>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<h2>결과 : 점수를 잘못 입력하였습니다. 다시 입력해주세요.</h2>
		</c:otherwise>
	 --%>
	<c:choose>
		<c:when test="${0 > param.score || param.score > 100}">
			<h2>시험점수 : ${param.score}점</h2>
			<h3>결과 : 점수를 잘못 입력하였습니다. 다시 입력해주세요.</h3>
			<a href="score.jsp">점수 입력 화면으로 이동</a>
		</c:when>
		<c:when test="${90 <= param.score}">
			<h2>시험점수 : ${param.score}점</h2>
			<h3>결과 : A학점</h3>
		</c:when>
		<c:when test="${80 <= param.score}">
			<h2>시험점수 : ${param.score}점</h2>
			<h3>결과 : B학점</h3>
		</c:when>
		<c:when test="${70 <= param.score}">
			<h2>시험점수 : ${param.score}점</h2>
			<h3>결과 : C학점</h3>
		</c:when>
		<c:when test="${60 <= param.score}">
			<h2>시험점수 : ${param.score}점</h2>
			<h3>결과 : D학점</h3>
		</c:when>
		<c:otherwise>
			<h2>시험점수 : ${param.score}점</h2>
			<h3>결과 : F학점</h3>
		</c:otherwise>
	</c:choose>
</body>
</html>
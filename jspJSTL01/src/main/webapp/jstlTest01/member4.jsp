<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%> 
<c:set var="id" value="hong" scope="page"/>
<c:set var="pwd" value="12345" scope="page"/>
<c:set var="name" value="홍길동" scope="page"/>
<c:set var="address" value="서울시 종로구" scope="page"/>
<c:set var="score" value="95" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>c:hoose if문과 비슷!</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="yellow">
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>주소</th>
		</tr>
		<c:choose>
			<c:when test="${empty name }">
				<tr align="center">
				<td colspan="4">이름을 입력해주세요!</td>         <!-- name이 빈칸이면(23~27라인)이 실행됨 -->
				</tr>
			</c:when>
			<c:when test="${score >= 90}">
				<tr align="center">
					<td>${id}</td>
					<td>${pwd}</td>
					<td>${name}</td>
					<td>${address}</td>
				</tr>
				<tr align="center">
				<td colspan="4">※ 성적 우수!!</td>
				</tr>
			</c:when>
			<c:when test="${score >= 60}">
				<tr align="center">
					<td>${id}</td>
					<td>${pwd}</td>
					<td>${name}</td>
					<td>${address}</td>
				</tr>
				<tr align="center">
				<td colspan="4">※ 성적 준수</td>
				</tr>
			</c:when>
			<c:when test="${score <= 59}">
				<tr align="center">
					<td>${id}</td>
					<td>${pwd}</td>
					<td>${name}</td>
					<td>${address}</td>
				</tr>
				<tr align="center">
				<td colspan="4">※ 매우 미흡</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr align="center">
					<td>${id}</td>
					<td>${pwd}</td>
					<td>${name}</td>
					<td>${address}</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>
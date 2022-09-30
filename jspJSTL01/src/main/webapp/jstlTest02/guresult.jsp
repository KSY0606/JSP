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
	<title>c:forEach 활용 예제</title>
</head>
<body>
	<c:set var="dan" value="${param.dan}"/>
	<table border="1" width="200" align="center">
		<tr align="center" bgcolor="lightgreen">
			<th colspan="2">
				<h3>*** ${dan}단 ***</h3>
			</th>
		</tr>
		<c:forEach var="i" begin="1" end="9" step="1">
		<c:if test="${i%2 == 0}">
			<tr align="center" bgcolor="#FFE400">
		</c:if>
		<c:if test="${i%2 == 1}">
			<tr align="center" bgcolor="#F15F5F">
		</c:if>
				<td>${dan} X ${i}</td>
				<td>${dan * i}</td>
		</c:forEach>
	</table>
</body>
</html>
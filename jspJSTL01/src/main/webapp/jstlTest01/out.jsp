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
<title>c:out 태그</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="yellow">
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th>
		</tr>
		<tr align="center">
			<td><c:out value="${param.id}"/></td>
			<td><c:out value="${param.pwd}"/></td>
			<td><c:out value="${param.name}"/></td>
			<td><c:out value="${param.email}"/></td>
		</tr>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, jspJSTL01.ex02.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원정보 목록</title>
</head>
<body>
	<table align="center" border="1">
		<tr align="center" bgcolor="lightgreen">
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>가입일</th>
		</tr>
		<c:choose>
			<c:when test="${memberList == null}">
				<tr>
					<td colspan="5">등록된 회원이 없습니다.</td>
				</tr>
			</c:when>
			<c:when test="${memberList != null}">
				<c:forEach var="mem" items="${memberList}">
					<tr align="center">
						<td>${mem.id}</td>
						<td>${mem.pwd}</td>
						<td>${mem.name}</td>
						<td>${mem.email}</td>
						<td>${mem.joinDate}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</body>
</html>
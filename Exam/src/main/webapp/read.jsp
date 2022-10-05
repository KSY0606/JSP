<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, exam.ex01.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 읽기</title>
</head>
<body>
	<h2>게시판을 읽어보세요.</h2>
	<table align="center" border="1">
		<tr align="center" bgcolor="lightgreen">
			<th>게시물 번호</th><th>작성자명</th><th>제목</th><th>내용</th><th>작성일</th><th>조회수</th>
		</tr>
		<c:choose>
			<c:when test="${read == null}">
				<tr>
					<td colspan="6" align="center">등록된 게시글이 없습니다.</td>
				</tr>
			</c:when>
			<c:when test="${read != null}">
				<c:forEach var="mem" items="${read}">
					<tr align="center">
						<td>${mem.num}</td>
						<td>${mem.name}</td>
						<td>${mem.subject}</td>
						<td>${mem.text}</td>
						<td>${mem.regdate}</td>
						<td>${mem.count}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</body>
</html>
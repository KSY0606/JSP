<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 창</title>
<script src="http://code.jquery.com/jquery-Latest.min.js"></script>
</head>
<body>
	<h2 align="center">글쓰기</h2>
	<form action="${contextPath}/board/addArticle.do" method="post" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td align="right">글제목 : </td>
				<td colspan="2"><input type="text" size="50" name="title"></td>
			</tr>
			<tr>
				<td align="right">글내용 : </td>
				<td colspan="2"><textarea rows="10" cols="50" maxlength="4000"></textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>
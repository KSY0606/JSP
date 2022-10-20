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
	<title>글 목록창</title>
<style type="text/css">
	.ba{
	text-decoration: none;
	}
	
</style>
</head>
<body>
	<table align="center" border="1" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<th>글 번호</th><th>작성자</th><th>제목</th><th>작성일</th>
		</tr>
		<c:choose>
			<c:when test="${empty articleList}">
				<tr>
					<td colspan="4">
						<p align="center">등록된 게시글이 없습니다.</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${!empty articleList}">
				<c:forEach var="article" items="${articleList}" varStatus="articleNum">
					<tr align="center">
						<td width="5%">${articleNum.count}</td>
						<td width="10%">${article.id}</td>
						<td align="left" width="45%">
							<c:choose>
								<c:when test="${article.level > 1}">
									<c:forEach begin="1" end="${article.level}" step="1">
										<span style="padding-left:10px"></span>
									</c:forEach>
									[답변]<a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
								</c:when>
								<c:otherwise>
									<a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td width="10%"><fmt:formatDate value="${article.writeDate}"/></td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<p align="center"><a href="${contextPath}/board/articleForm.do">글쓰기</a>
</body>
</html>
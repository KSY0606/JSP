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
<title>글 상세 보기</title>
<script src="http://code.jquery.com/jquery-Latest.min.js"></script>
<script type="text/javascript">
	function readImage(input) {
		if(input.files && input.files[0]) {
			let reader = new FileReader();
			reader.onload = function(event) {
				$('#preview').attr('src',event.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function toList(obj) {
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
</script>
</head>
<body>
	<form name="frmArticle" action="${contextPath}" method="post" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">글번호</td>
				<td><input type="text" value="${article.articleNo}" name="articleNo" disabled></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">작성자아이디</td>
				<td><input type="text" value="${article.id}" name="writer" disabled></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">제목</td>
				<td><input type="text" value="${article.title}" name="title" disabled></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">내용</td>
				<td>
					<textarea rows="20" cols="60" name="content" disabled>${article.content}</textarea>
				</td>
			</tr>
			<c:if test="${not empty article.imageFileName && article.imageFileName != 'null'}">
				<tr>
					<td width="150" rowspan="2" align="center" bgcolor="#ff9933">이미지</td>
					<td>
						<img src="${contextPath}/download.do?articleNo=${article.articleNo}&imageFileName=${article.imageFileName}" id="preview" width="300"><br>
					</td>
				</tr>
				<tr>
					<td>
					<input type="file" name="imageFileName" onchange="readImage(this)" disabled>
					</td>
				</tr>
			</c:if>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">등록일자</td>
				<td><input type="text" value="<fmt:formatDate value="${article.writeDate}"/>" disabled></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onclick="">
					<input type="button" value="삭제하기" onclick="">
					<input type="button" value="리스트로 돌아가기" onclick="toList(this.form)">
					<input type="button" value="답글쓰기" onclick="">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
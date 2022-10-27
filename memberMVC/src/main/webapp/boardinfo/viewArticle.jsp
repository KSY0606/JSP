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
<style type="text/css">
	#tr_button_modify {
		display:none;
	}
</style>
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
	function fn_enable(obj) {
		document.getElementById("id_title").disable = false;
		document.getElementById("id_content").disable = false;
		let imgName = document.getElementById("id_imageFileName");
		if(imgName != null) {
			imgName.disable = false;
		}
		document.getElementById("tr_button_modify").style.display="block";
		document.getElementById("tr_button").style.display="none";
	}
	function fn_modify_article(obj) {
		obj.action = "${contextPath}/board/modArticle.do";
		obj.submit();
	}
	function fn_remove_article(url, articleNo) {
		let form = document.createElement("form");
		form.setAttribute("method","post");
		form.setAttribute("action", url);
		let articleNoInput = document.createElement("input");
		articleNoInput.setAttribute("type","hidden");
		articleNoInput.setAttribute("name","articleNo");
		articleNoInput.setAttribute("value",articleNo);
		form.appendChild(articleNoInput);
		document.body.appendChild(form);
		form.submit();
	}
	function fn_reply_form(url, parentNo) {
		let form = document.createElement("form");
		form.setAttribute("method","post");
		form.setAttribute("action",url);
		let parentNoInput = document.createElement("input");
		parentNoInput.setAttribute("type","hidden");
		parentNoInput.setAttribute("name","parentNo");
		parentNoInput.setAttribute("value", parentNo);
		form.appendChild(parentNoInput);
		document.body.appendChild(form);
		form.submit();
	}
</script>
</head>
<body>
	<form name="frmArticle" action="${contextPath}" method="post" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">글번호</td>
				<td><input type="text" value="${article.articleNo}" disabled></td>
				<input type="hidden" name="articleNo" value="${article.articleNo}">
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">작성자아이디</td>
				<td><input type="text" value="${article.id}" name="writer" disabled></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">제목</td>
				<td><input type="text" value="${article.title}" name="title" id="id_title" disabled></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">내용</td>
				<td>
					<textarea rows="20" cols="60" name="content" id="id_content" disabled>${article.content}</textarea>
				</td>
			</tr>
			<c:if test="${not empty article.imageFileName && article.imageFileName != 'null'}">
				<tr>
					<td width="150" rowspan="2" align="center" bgcolor="#ff9933">이미지</td>
					<input type="hidden" name="originalFileName" value="${article.imageFileName}">
					<td>
						<img src="${contextPath}/download.do?articleNo=${article.articleNo}&imageFileName=${article.imageFileName}" id="preview" width="300"><br>
					</td>
				</tr>
				<tr>
					<td>
					<input type="file" name="imageFileName" id="id_imageFileName" onchange="readImage(this)" disabled>
					</td>
				</tr>
			</c:if>
			<tr>
				<td width="150" align="center" bgcolor="#ff9933">등록일자</td>
				<td><input type="text" value="<fmt:formatDate value="${article.writeDate}"/>" disabled></td>
			</tr>
			<tr id="tr_button_modify">
				<td colspan="2" align="center">
					<input type="button" value="수정반영하기" onclick="fn_modify_article(frmArticle)">
					<input type="button" value="취소" onclick="toList(frmArticle)">
				</td>
			</tr>
			<tr id="tr_button">
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onclick="fn_enable(this.form)">
					<input type="button" value="삭제하기" onclick="fn_remove_article('${contextPath}/board/removeArticle.do',${article.articleNo})">
					<input type="button" value="리스트로 돌아가기" onclick="toList(this.form)">
					<input type="button" value="답글쓰기" onclick="fn_reply_form('${contextPath}/board/replyForm.do',${article.articleNo})">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
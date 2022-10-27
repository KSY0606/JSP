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
<title>답글쓰기 창</title>
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
	// 다른 액션을 submit하는 함수
	function toList(obj) {
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
</script>
</head>
<body>
	<h2 align="center">답글쓰기</h2>
	<form name="frmReply" action="${contextPath}/board/addReply.do" method="post" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td align="right">글쓴이 : </td>
				<td><input type="text" value="hong" disabled></td>
			</tr>
			<tr>
				<td align="right">글제목 : </td>
				<td colspan="2"><input type="text" size="50" name="title"></td>
			</tr>
			<tr>
				<td align="right">글내용 : </td>
				<td colspan="2"><textarea name="content" rows="10" cols="50" maxlength="4000"></textarea></td>
			</tr>
			<tr>
				<td align="center">이미지파일 첨부 : </td>
				<td><input type="file" name="imageFileName" onchange="readImage(this)"></td>
				<td><img id="preview" src="#" width="200" height="200"> </td>
			</tr>
			<tr>
				<td align="right">
					<input type="submit" value="답글반영하기">
				</td>
				<td align="center">
					<input type="button" value="취소" onclick="toList(this.form)">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
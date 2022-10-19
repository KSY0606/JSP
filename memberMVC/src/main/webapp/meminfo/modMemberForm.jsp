<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정창</title>
</head>
<body>
	<form action="${contextPath}/member/modMember.do?id=${memFindInfo.id}" method="post">
		<h2 align="center">회원정보 수정창</h2>
		<table align="center">
			<tr>
				<td width="200">아이디</td>
				<td width="400"><input type="text" name="id" value="${memFindInfo.id}" disabled></td> <!-- disable  수정불가능하게 함 -->
			</tr>
			<tr>
				<td width="200">비밀번호</td>
				<td width="400"><input type="password" name="pwd" value="${memFindInfo.pwd}"></td>
			</tr>
			<tr>
				<td width="200">이름</td>
				<td width="400"><input type="text" name="name" value="${memFindInfo.name}"></td>
			</tr>
			<tr>
				<td width="200">이메일</td>
				<td width="400"><input type="text" name="email" value="${memFindInfo.email}"></td>
			</tr>
			<tr>
				<td width="200">가입일</td>
				<td width="400"><input type="text" name="joinDate" value="${memFindInfo.joinDate}" disabled></td>
			</tr>
			<tr>
				<td width="200">&nbsp;</td>
				<td width="400">
				<input type="submit" value="수정하기">
				<input type="reset" value="다시입력">
				<td>
			</tr>
		</table>
	</form>
</body>
</html>
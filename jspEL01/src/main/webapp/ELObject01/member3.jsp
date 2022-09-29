<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, jspEL01.ex01.*" 
    isELIgnored="false"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="memBean" class="jspEL01.ex02.MemberBean"></jsp:useBean>
<jsp:setProperty property="*" name="memBean"/>
<jsp:useBean id="addr" class="jspEL01.ex02.Address"></jsp:useBean>
<jsp:setProperty property="city" value="서울" name="addr"/>
<jsp:setProperty property="zipcode" value="01234" name="addr"/>
<%
	memBean.setAddr(addr);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="yellow">
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>도시</th><th>우편번호</th>
		</tr>
		<tr align="center">
			<td>${memBean.id}</td>
			<td>${memBean.pwd}</td>
			<td>${memBean.name}</td>
			<td>${memBean.email}</td>
			<td><%=memBean.getAddr().getCity() %></td>
			<td><%=memBean.getAddr().getZipcode() %></td>
		</tr>
		<tr align="center">
			<td>${memBean.id}</td>
			<td>${memBean.pwd}</td>
			<td>${memBean.name}</td>
			<td>${memBean.email}</td>
			<td>${memBean.addr.city}</td>
			<td>${memBean.addr.zipcode}</td>
		</tr>
	</table>
</body>
</html>
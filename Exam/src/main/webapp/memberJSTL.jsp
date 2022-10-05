<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, exam.ex01.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
	<jsp:useBean id="mBean" class="exam.ex01.MemberBean"/>
	<jsp:setProperty property="*" name="mBean"/>
	<%
		MemberDAO memDAO = new MemberDAO();
		memDAO.addMember(mBean);
		List read = memDAO.read();
		request.setAttribute("read", read);
	%>
</head>
<body>
<jsp:forward page="read.jsp"></jsp:forward>
</body>
</html>
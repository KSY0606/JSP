<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, jspJSTL01.ex02.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원관리 JSTL 실습</title>
	<jsp:useBean id="mBean" class="jspJSTL01.ex02.MemberBean"/>
	<jsp:setProperty property="*" name="mBean"/>
	<%
		MemberDAO memDAO = new MemberDAO();
		memDAO.addMember(mBean);
		List memberList = memDAO.listMembers();
		request.setAttribute("memberList", memberList);
	%>
</head>
<body>
<jsp:forward page="memberList.jsp"></jsp:forward>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="jspEL01.ex01.*"
%>
    <%
    	request.setCharacterEncoding("utf-8");
    	MemberBean member = new MemberBean("lee", "1234", "이강인", "lee@gmail.com");
    	request.setAttribute("member", member);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="member2.jsp"></jsp:forward>
</body>
</html>
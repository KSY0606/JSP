<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:redirect url="member.jsp">
	<c:param name="id" value="hong"></c:param>
	<c:param name="pwd" value="1234"></c:param>
	<c:param name="name" value="홍길동"></c:param>
	<c:param name="email" value="hong@gmail.com"></c:param>
</c:redirect>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>redirect</title>
</head>
<body>
	
</body>
</html>
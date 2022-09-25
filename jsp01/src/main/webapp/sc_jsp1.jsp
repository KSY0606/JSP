<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String name = "김상윤";
	public String getName() {
		return name + "님 안녕하세요";
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>선언문</title>
</head>
<body>
	<h1><%=name %>님 환영합니다.</h1> <!-- = 를 붙여주고 변수를 써야함 --> 
	<h2><%=getName() %></h2>
</body>
</html>
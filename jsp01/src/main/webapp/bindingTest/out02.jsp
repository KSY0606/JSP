<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    	request.setCharacterEncoding("utf-8");
    	String name = request.getParameter("name");
    	String age = request.getParameter("age");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 출력</title>
</head>
<body>
	<h2>당시의 이름은 <%=name %>이고, 나이는 <%=age %>살이군요.</h2> 
	<h2><%out.print("당신의 이름은 " + name + "이고 나이는 " + age + "살이군요."); %></h2>  <!-- jsp의 writer를 이용해 출력 -->
</body>
</html>
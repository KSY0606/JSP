<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
	int dan = Integer.parseInt(request.getParameter("dan"));
    %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>구구단 출력결과</title>
</head>
<body>
	<h2> *** <%=dan %> 단 ***</h2>
	<%
		for(int i = 1; i <= 9; i++) {
	%>
	<p><%=dan %> X <%=i%> = <%=dan * i%></p>
	<%
		}
	%>
	
</body>
</html>
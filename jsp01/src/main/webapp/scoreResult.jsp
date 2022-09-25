<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
	int score = Integer.parseInt(request.getParameter("score"));
    %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
<title>시험 결과보기</title>
</head>
<body>
	<%
		if(score >= 90) {
	%>
	<p>시험점수는 <%=score %>점 입니다.</p>
	<p>결과는 A학점입니다.</p>
	<%
		} else if(score >= 80 && score <= 89) {
	%>
	<p>시험점수는 <%=score %>점 입니다.</p>
	<p>결과는 B학점입니다.</p>
	<%
		} else if(score >= 70 && score <= 79) {
	%>
	<p>시험점수는 <%=score %>점 입니다.</p>
	<p>결과는 C학점입니다.</p>
	<%
		} else if(score >= 60 && score <= 69) {
	%>
	<p>시험점수는 <%=score %>점 입니다.</p>
	<p>결과는 D학점입니다.</p>
	<%
		} else {
	%>
	<p>시험점수는 <%=score %>점 입니다.</p>
	<p>결과는 F학점입니다.</p>
	<%
		}
	%>
</body>
</html>
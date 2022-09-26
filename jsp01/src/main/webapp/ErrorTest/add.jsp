<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage="addException.jsp"%>    <!-- 에러 발생시 addException.jsp를 호출해줌! -->
    <%
    	int num = Integer.parseInt(request.getParameter("num"));  // num에 숫자말고 문자 입력시 500에러 발생
    	int sum = 0;
    	for(int i = 1; i <= num; i++) {
    		sum += i;
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>합계 구하기</title>
</head>
<body>
	<h2>1부터 <%=num %>까지의 합은 = <%=sum %>입니다.</h2>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산술연산자</title>
</head>
<body>
	<h2>
		\${20+30} : ${20+30}<br>
		\${50-23} : ${50-23}<br>
		\${5*8} : ${5*8}<br>
		
		\${25/3} : ${25/3}<br>
		\${25 div 3} : ${25 div 3}<br>
		
		\${25%3} : ${25%3}<br>					<%-- /와 div는 나누기  %와 mod는 나머지값을 구해줌 --%>
		\${25 mod 3} : ${25 mod 3}<br>  
	</h2>
</body>
</html>
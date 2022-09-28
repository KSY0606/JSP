<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>논리연산자</title>
</head>
<body>
	\${50==50 && 20==20} : ${50==50 && 20==20} <br>
	\${50==50 and 20==20} : ${50==50 and 20==20} <br>
	
	\${50==50 || 20==20} : ${50==50 || 20==20} <br>
	\${50==50 or 20==20} : ${50==50 or 20==20} <br>
	
	\${!(80==50)} : ${!(80==50)} <br>
	\${not(80==50)} : ${not(80==50)} <br>
</body>
</html>
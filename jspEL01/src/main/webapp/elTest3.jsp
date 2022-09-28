<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비교연산자</title>
</head>
<body>
	\${30==30} : ${30==30} <br>
	\${30 eq 30} : ${30 eq 30} <br>		  <%-- ==와 eq는 값이 같은지 true, false로 출력됨 --%>
	
	\${"kbs"=="kbs"} : ${"kbs"=="kbs"} <br>
	\${"kbs" eq "kbs"} : ${"kbs" eq "kbs"} <br>
	
	\${10!=20} : ${10!=20} <br>
	\${10 ne 20} : ${10 ne 20} <br>
	
	\${50>20} : ${50>20} <br>
	\${50 gt 20} : ${50 gt 20} <br>
	
	\${50<20} : ${50<20} <br>
	\${50 lt 20} : ${50 lt 20} <br>
	
	\${20>=20} : ${20>=20} <br>
	\${20 ge 20} : ${20 ge 20} <br>
	
	\${20<=20} : ${20<=20} <br>
	\${20 le 20} : ${20 le 20} <br>
</body>
</html>
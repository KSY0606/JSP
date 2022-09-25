<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%! String name = "김상윤"; %>
    <% String age = request.getParameter("age"); %>  <!-- 스크립트릿이라고 함 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언문 실습</title>
</head>
<body>
	<h2>여러분 안녕하세요 제 이름은 <%=name %>입니다.</h2>   <!-- <%=name %>  는 표현식 이라고 함. -->
	<h2>나이는 <%=age %>세 입니다. </h2>    <!-- 주소창에 ?age=28 을 적으면 출력됨 -->
	<h2>제 신장은 <%=175 %>cm 입니다.</h2>
	<h2>10년 후 제 나이는 <%=Integer.parseInt(age) + 10 %>입니다.</h2>   <!-- 표현식 양 옆에 <%-- --%> 를 써주면 주석 -->
</body>
</html>
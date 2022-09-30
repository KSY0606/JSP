<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import= "java.util.*"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("utf-8");
%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>format 태그 라이브러리</title>
	
</head>
<body>
	<h2>
		<c:set var="price" value="3500000"/>
		<fmt:formatNumber var="priceNum" value="${price}" type="number"/>  <%-- type="number"면 세자리마다 , 를 해줌!! --%>
		상품 가격 : ${priceNum}원
	</h2>
	<h2>
		<c:set var="rate" value="0.2"/>
		<fmt:formatNumber var="rateNum" value="${rate}" type="percent"/>  <%-- type="percent"면 %처리를 해줌!!! --%>
		할인율 : ${rateNum}
	</h2>
	<h2>
		<c:set var="price2" value="2300000"/>
		<fmt:formatNumber var="priceNum2" value="${price2}" type="currency" currencySymbol="$"/>  <%-- type="currency"면 화폐기호 --%>
		상품 가격 : ${priceNum2}										<%-- 직접 화폐모양을 바꾸고 싶으면  currencySymbol="$"처럼 " " 안에 입력 --%>
	</h2>
	<h2>
		<c:set var="now" value="<%= new Date() %>"/>
		<fmt:formatDate var="fnow" value="${now}" type="date"/>
		<fmt:formatDate var="fnow1" value="${now}" type="date" dateStyle="full"/> 
		<fmt:formatDate var="fnow2" value="${now}" type="time"/>
		<fmt:formatDate var="fnow3" value="${now}" type="both"/>
		오늘 : ${fnow} <br>
		오늘 : ${fnow1} <br>
		현재시간 : ${fnow2} <br>
		현재 : ${fnow3} <br>
		
		<fmt:timeZone value="America/New York">
			뉴욕 현재시간 : <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
		</fmt:timeZone>
	</h2>
</body>
</html>
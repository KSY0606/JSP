<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON 테스트</title>
<script src="http://code.jquery.com/jquery-Latest.min.js"></script>
<script type="text/javascript">
	$(function () {
		$("input").click(function () {
			$.ajax({
				type:"post",
				async:false,
				url: "${contextPath}/json3",
				success:function (data, textStatus) {
					let jsonInfo = JSON.parse(data);
					//회원정보
					let memInfo = "<h2>**회원정보**</h2>"
					memInfo += "=============<br>";
					for(let i in jsonInfo.member) {
						memInfo += "이름 : " + jsonInfo.member[i].name + "<br>";
						memInfo += "나이 : " + jsonInfo.member[i].age + "<br>";
						memInfo += "직업 : " + jsonInfo.member[i].job + "<br>";
						memInfo += "===========<br>";
					}
					// 도서정보
					let bookInfo = "<h2>**도서정보**</h2>";
					bookInfo += "=============<br>";
					for(let i in jsonInfo.books) {
						bookInfo += "제목 : " + jsonInfo.books[i].title + "<br>";
						bookInfo += "출판사 : " + jsonInfo.books[i].writer + "<br>";
						imageURL=jsonInfo.books[i].image;
						bookInfo += "<img src=" + imageURL + " width=300 height=400><br>";
						bookInfo += "===========<br>";
					}
					$("#output").html(memInfo + "<br>" + bookInfo);
				},
				error:function (data, textStatus) {
					alert("에러가 발생했습니다.");
				}
			});
		});
	});
</script>
</head>
<body>
	<input type="button" value="회원정보 수신하기">
	<div id="output"></div>
</body>
</html>
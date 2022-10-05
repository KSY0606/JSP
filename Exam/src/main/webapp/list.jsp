<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>리스트 페이지</title>
	<script type="text/javascript">
		function add_notice() {
			let notice = document.notice;
				notice.method="post";
				notice.action="post.jsp";
				notice.submit();
		}
		
		function read_notice() {
			let notice = document.notice;
				notice.method="post";
				notice.action="read.jsp";
				notice.submit();
		}
	</script>
</head>
<body>
	<form name="notice" >
		<h2>게시판</h2>
		<input type="button" value="글 작성하기" onclick="add_notice()">
		<input type="button" value="게시판 읽기" onclick="read_notice()">
	</form>
</body>
</html>
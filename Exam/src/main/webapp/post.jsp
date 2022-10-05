<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 글 작성</title>
	<script type="text/javascript">
	function fn_registration() {
		let registration = document.registration;
		let num = registration.num.value;
		let name = registration.name.value;
		let subject = registration.subject.value;
		let text = registration.text.value;
		let pass = registration.pass.value;
		let count = registration.count.value;
		if(subject.length == 0 || subject == "") {
			alert("제목을 입력해주세요.");
		}else if(text.length == 0 || text == "") {
			alert("내용을 입력해주세요.")
		}else {
			registration.method="post";
			registration.action="memberJSTL.jsp";
			registration.submit();
		}
	}
</script>
</head>
<body>
	<form name="registration">
		<table>
			<th>게시글을 남겨주세요.</th>
			<tr>
				<td>게시물 번호</td>
				<td><input type="number" name="num"></td>
			</tr>
			<tr>
				<td>작성자명</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea cols="50" rows="10" name="text"></textarea></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td>조회수</td>
				<td><input type="number" name="count"></td>
			</tr>
		</table>
		<input type="button" value="등록하기" onclick="fn_registration()">
		<input type="reset" value="다시입력">
	</form>
</body>
</html>
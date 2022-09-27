<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page import="jsp01.ex02.MemberDAO"%>
<%@page import="jsp01.ex02.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String _name = request.getParameter("name");
	MemberVO memberVO = new MemberVO();
	memberVO.setName(_name);
	MemberDAO dao = new MemberDAO();
	List memberList = dao.listMembers(memberVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 출력창</title>
</head>
<body>
	<h1>회원 정보 출력</h1>
	<table border="1" width="700px" align="center">
	<tr align="center" bgcolor="#FAF4C0">
		<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>가입일</th>
	</tr>
	<%
		for(int i = 0; i < memberList.size(); i++) {
			MemberVO vo = (MemberVO)memberList.get(i);
			String id = vo.getId();
			String pwd = vo.getPwd();
			String name = vo.getName();
			String email = vo.getEmail();
			Date joindate = vo.getJoinDate();
	%>
	<tr align="center">
		<td><%=id %></td>
		<td><%=pwd %></td>
		<td><%=name %></td>
		<td><%=email %></td>
		<td><%=joindate%></td>
	</tr>
	<%
		}
	%>
	</table>
</body>
</html>
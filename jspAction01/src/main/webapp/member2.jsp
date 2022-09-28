<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import ="java.util.*, jspAction01.ex01.*" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="memBean" class="jspAction01.ex01.MemberBean" scope="page"></jsp:useBean>
<%
	String command = request.getParameter("command");
	MemberDAO dao = new MemberDAO();
	if(command != null && command.equals("addMember")){
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		memBean.setId(id);
		memBean.setPwd(pwd);
		memBean.setName(name);
		memBean.setEmail(email);
		dao.addMember(memBean);
	} else {
		String id = request.getParameter("id");
		dao.delMember(id);
	}
	List memberList = dao.listMembers(); // 목록 보여줌
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
	<h2 align="center">회원 목록</h2>
	<table border="1" align="center">
		<tr align="center" bgcolor="yellow">
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>가입일</th><th>삭제</th>
		</tr>
		<%
			if(memberList.size() == 0) {
		%>
		<tr><td colspan="5">
		<p align="center">등록된 회원이 없습니다.</p>
		</td></tr>
		<%
			} else {
				for(int i = 0; i < memberList.size(); i++) {
					MemberBean bean = (MemberBean)memberList.get(i);
		%>
			<tr align="center">
				<td><%=bean.getId() %></td>
				<td><%=bean.getPwd() %></td>
				<td><%=bean.getName() %></td>
				<td><%=bean.getEmail() %></td>
				<td><%=bean.getJoinDate() %></td>
				<td><a href="/jspAction01/member.jsp?command=delMember&id=<%=bean.getId() %>">삭제</a></td>
			</tr>
		<%
				}
			}
		%>
	</table>
	<a href="/jspAction01/memberForm.html">새 회원 등록하기</a>
</body>
</html>
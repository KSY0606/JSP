package servletfw.ex03;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		List memberList = (List)request.getAttribute("memberList");
		out.print("<html><body>");
		out.print("<table border = 1>");
		out.print("<tr align = 'center' bgcolor = '#CEFBC9'>");
		out.print("<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>가입일</th><th>삭제</th>");
		out.print("</tr>");
		for(int i = 0; i < memberList.size(); i++) {
			MemberVO memberVO = (MemberVO)memberList.get(i);
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
			out.print("<tr><td>" + id + "</td><td>" + pwd + "</td><td>" + name + "</td><td>" + email + "</td><td>" + joinDate + "</td><td>" + "<a href='/servletfw/member3?command=delMember&id=" + id + "'>삭제</a></td></tr>");
		}
		out.print("</table>");
		out.print("<a href='/servletfw/memberForm.html'>새 회원 등록하기</a>");
		out.print("</body></html>");
	}

}

package servletlink.ex05;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/logintest")
public class LoginsServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberVO);
		out.print("<html><body>");
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("log_id", user_id);
			out.print(user_id + "�� ȯ���մϴ�.<br>");
			out.print("<a href='loginTest.html'>�α׾ƿ�</a>");
		}else {
			out.print("<p>ȸ�� ������ Ʋ���ϴ�.</p>");
			out.print("<a href='loginTest.html'>�ٽ� �α����ϱ�</a>");
		}
		out.print("</body></html>");
	}
}

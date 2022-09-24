package servletlink.ex05;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/showlogin")
public class ShowMember extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = "", pwd = "";
		boolean isLogon = false;
		HttpSession session = request.getSession(false);
		if(session != null) {
			isLogon = (Boolean)session.getAttribute("isLogon");
			if(isLogon == true) {
				id = (String)session.getAttribute("log_id");
				out.print("<html><body>");
				out.print(id + "���� ���ȸ���̶� ���α��� �ֽ��ϴ�.<br>");
				out.print("���α��� �̿��ؼ� ������ ��ܺ�����");
				out.print("</body></html>");
			}else {
				response.sendRedirect("login4.html");
			}
		}else {
			response.sendRedirect("login4.html");
		}
	}

}

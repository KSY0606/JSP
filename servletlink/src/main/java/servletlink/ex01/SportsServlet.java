package servletlink.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sports") 							   // URL Rewriting ����           LoginServlet2, login.html ���� �� ��
public class SportsServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		out.print("<html><body>");
		if(user_id != null && user_id.length() != 0) {
			out.print(user_id + "���� �α����� ����<br>");
			out.print("������ �߰� ����� �ۼ� �� �� �ֽ��ϴ�.");
		}else {
			out.print("�α������� �ʾҽ��ϴ�.<br>");
			out.print("�α������ּ���<br>");
			out.print("<a href='/servletlink/login.html'>�α���â���� �̵��ϱ�</a>");
		}
		out.print("</body></html>");
	}

}

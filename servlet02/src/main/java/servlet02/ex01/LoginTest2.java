package servlet02.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginTest2")
public class LoginTest2 extends HttpServlet {


	public void init(ServletConfig config) throws ServletException {
		System.out.println("init �޼��� ȣ��");
	}

	public void destroy() {
		System.out.println("destroy �޼��� ȣ��");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		System.out.println("���̵� : " + id);
		System.out.println("��й�ȣ : " + pw);
		if(id != null && (id.length() != 0)) {
			if(id.equals("admin")) {
				out.print("<html>");
				out.print("<body>");
				out.print("<font size='7'>�����ڷ� �α��� �ϼ̽��ϴ�.</font><br>");
				out.print("<input type=button value='ȸ������ �����ϱ�'>");
				out.print("<input type=button value='ȸ������ �����ϱ�'>");
				out.print("</body>");
				out.print("</html>");
			}else {
				out.print("<html>");
				out.print("<body>");
				out.print("<p>" + id + "���� �α��� �ϼ̽��ϴ�.</p>");
				out.print("</body>");
				out.print("</html>");
			}
		}else {
			out.print("<html>");
			out.print("<body>");
			out.print("<p>���̵� �Է��ϼ���.</p>");
			out.print("<a href='http://localhost:8090/servlet02/login3.html'>�α���â���� �̵�</a>");
			out.print("</body>");
			out.print("</html>");
		}
	}

}

package servletfw.ex02;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/bsecond")
public class SecondServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset:utf-8");
		String name=(String)request.getAttribute("name");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println(name + "�� ȯ���մϴ�.<br>");
		out.println("dispatch�� �̿��� ���ε� �ǽ��Դϴ�.");
		out.println("</body></html>");
	}

}

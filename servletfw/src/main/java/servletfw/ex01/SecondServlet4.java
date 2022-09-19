package servletfw.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/second4")
public class SecondServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset:utf-8");
		String name = request.getParameter("name");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<p>" + name + "님 환영합니다.</p>");
		out.println("<p>dispatch를 이용한 forward 실습입니다.</p>");
		out.println("</body></html>");
	}

}

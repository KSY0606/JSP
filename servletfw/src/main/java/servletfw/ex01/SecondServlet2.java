package servletfw.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/second2")
public class SecondServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<p>refresh를 이용한 redirect 실습입니다.");
		out.println("</body></html>");
	}

}

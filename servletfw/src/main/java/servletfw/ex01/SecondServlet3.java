package servletfw.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/second3")
public class SecondServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<p>location�� �̿��� redirect �ǽ��Դϴ�.");
		out.println("</body></html>");
	}

}

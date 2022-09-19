package servletfw.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/first2")
public class FirstServlet2 extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset:utf-8");
		PrintWriter out = response.getWriter();
		response.addHeader("refresh", "1;url=second2");  // 1초후에 second2라는 서블릿을 재요청한다는 뜻
	}

}

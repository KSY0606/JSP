package jspFile.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/first/test")
public class URLTestServlet01 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String context = request.getContextPath();
		String url = request.getRequestURL().toString();
		String mapping = request.getServletPath();
		String uri = request.getRequestURI();
		out.print("<html><body bgcolor='lightgreen'>");
		out.print("<p>URLTestServlet01</p>");
		out.print("<p>컨텍스트 이름 : " + context + "</p>");
		out.print("<p>전체 경로 : " + url + "</p>");
		out.print("<p>매핑 이름 : " + mapping + "</p>");
		out.print("<p>URI : " + uri + "</p>");
		out.print("</body></html>");	
	}

}

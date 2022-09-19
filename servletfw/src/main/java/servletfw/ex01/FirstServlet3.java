package servletfw.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/first3")
public class FirstServlet3 extends HttpServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset:utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script type='text/javascript'>");
		out.print("location.href='second3';");
		out.print("</script>");
	}

}

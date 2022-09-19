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
		out.println(name + "님 환영합니다.<br>");
		out.println("dispatch를 이용한 바인딩 실습입니다.");
		out.println("</body></html>");
	}

}

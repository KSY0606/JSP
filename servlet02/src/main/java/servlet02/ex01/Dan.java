package servlet02.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/gugu")
public class Dan extends HttpServlet {


	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}

	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		int num = Integer.parseInt(request.getParameter("dan"));
		out.print("<html>");
		out.print("<body>");
		out.print("<p>*** " + num + "단 ***</p>");
		for(int i = 1; i <= 9; i++) {
			out.print("*** " + num + " * " + i + " = " +  num *i + " ***<br>");
		}
		out.print("</body>");
		out.print("</html>");
	}

}

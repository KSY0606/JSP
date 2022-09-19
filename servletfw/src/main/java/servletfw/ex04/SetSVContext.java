package servletfw.ex04;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/setC")
public class SetSVContext extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		ServletContext context = getServletContext();
		List member = new ArrayList<>();
		member.add("홍길동");
		member.add(50);
		context.setAttribute("member", member);
		out.print("<html><body>");
		out.print("이름과 나이를 설정함");
		out.print("</body></html>");
	}

}

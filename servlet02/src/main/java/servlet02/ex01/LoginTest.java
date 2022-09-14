package servlet02.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
	


	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}

	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
		if(id != null && (id.length() != 0)) {
			out.print("<html>");
			out.print("<body>");
			out.print("<p>" + id + "님이 로그인 하셨습니다.</p>");
			out.print("</body>");
			out.print("</html>");
		}else {
			out.print("<html>");
			out.print("<body>");
			out.print("<p>아이디를 입력하세요.</p>");
			out.print("<a href='http://localhost:8090/servlet02/login3.html'>로그인창으로 이동</a>");
			out.print("</body>");
			out.print("</html>");
		}
	}

}

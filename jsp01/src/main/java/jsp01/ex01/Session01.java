package jsp01.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/sess01")
public class Session01 extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("name", "홍길동");
		out.print("<html><body>");
		out.print("<h1>세선에 이름을 바인딩합니다.</h1>");
		out.print("<a href='/jsp01/bindingTest/session01.jsp'>첫 번째 페이지로 이동하기</a>"); // sessionTest 폴더를 안만들었을 경우, session01.jsp만 써줘도됨
		out.print("</body></html>");
	}

}

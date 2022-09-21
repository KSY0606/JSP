package servletlink.ex03;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login4")
public class SessionTest4 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); // 세션 생성
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		if(session.isNew()) {           // isnew : 최초 요청시 실행
			if(user_id != null) {
				session.setAttribute("user_id", user_id);  // (키, 값)
				String url = response.encodeURL("login4");
				out.print("<a href=" + url + ">로그인 상태 확인</a>");
			}else {
				out.print("<a href='login2.html'>다시 로그인하세요!</a>");
			}
		}else {
			user_id = (String)session.getAttribute("user_id");
			if(user_id != null && user_id.length() != 0) {
				out.print("안녕하세요   " + user_id + "님의 방문을 환영합니다.");
			}else {
				out.print("<a href='login2.html'>다시 로그인하세요!</a>");
				session.invalidate();
			}
		}
	}
}

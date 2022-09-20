package servletlink.ex02;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/set2")									// SetCookie2, GetCookie2 같이 볼것    session쿠키 예제
public class SetCookie2 extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		Cookie ck = new Cookie("cookieTest2", URLEncoder.encode("하늘이 맑아요^^","utf-8"));
		ck.setMaxAge(-1);  // setMaxAge = 쿠키 유효시간 ,   setMaxAge를 음수로 지정할시 session쿠키로 됨
		response.addCookie(ck);  // 생성된 쿠키가 브라우저로 전송됨
		out.print("쿠키 생성된 시간 : " + date);
		out.print("쿠키가 저장되었습니다.");
	}

}

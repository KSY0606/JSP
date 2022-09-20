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


@WebServlet("/set")									// SetCookie, GetCookie 같이 볼것    persistence쿠키 예제
public class SetCookie extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		Cookie ck = new Cookie("cookieTest", URLEncoder.encode("가을날씨좋아요^^","utf-8"));
		ck.setMaxAge(24*60*60);  // setMaxAge = 쿠키 유효시간 ,   setMaxAge를 설정 할 시 persistence쿠키로 만드는 것 
		response.addCookie(ck);  // 생성된 쿠키가 브라우저로 전송됨
		out.print("쿠키 생성된 시간 : " + date);
		out.print("쿠키가 저장되었습니다.");
	}

}

package servletlink.ex02;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/get2")									// SetCookie, GetCookie 같이 볼것    session쿠키 예제
public class GetCookie2 extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Cookie[] value = request.getCookies();
		for(int  i = 0; i < value.length; i++) {
			if(value[i].getName().equals("cookieTest2")) {
				out.print("<h3>쿠키값 : " + URLDecoder.decode(value[i].getValue(), "utf-8") + "</h3>");
			}
		}
	}

}

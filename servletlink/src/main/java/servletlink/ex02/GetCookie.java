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


@WebServlet("/get")									// SetCookie, GetCookie ???? ????    persistence??Ű ????
public class GetCookie extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Cookie[] value = request.getCookies();
		for(int  i = 0; i < value.length; i++) {
			if(value[i].getName().equals("cookieTest")) {
				out.print("<h3>??Ű?? : " + URLDecoder.decode(value[i].getValue(), "utf-8") + "</h3>");
			}
		}
	}

}

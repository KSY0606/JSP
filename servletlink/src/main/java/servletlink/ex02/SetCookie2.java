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


@WebServlet("/set2")									// SetCookie2, GetCookie2 ���� ����    session��Ű ����
public class SetCookie2 extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		Cookie ck = new Cookie("cookieTest2", URLEncoder.encode("�ϴ��� ���ƿ�^^","utf-8"));
		ck.setMaxAge(-1);  // setMaxAge = ��Ű ��ȿ�ð� ,   setMaxAge�� ������ �����ҽ� session��Ű�� ��
		response.addCookie(ck);  // ������ ��Ű�� �������� ���۵�
		out.print("��Ű ������ �ð� : " + date);
		out.print("��Ű�� ����Ǿ����ϴ�.");
	}

}

package servletlink.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/login")                                // <hidden>�±�
public class LoginServlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_hp = request.getParameter("user_hp");
		String user_email = request.getParameter("user_email");
		String user_address = request.getParameter("user_address");
		String data = "<html><body>";
		data += "�α����Ͽ����ϴ�.<br>";
		data += "���̵� : " + user_id + "<br>";
		data += "��й�ȣ : " + user_pw + "<br>";
		data += "�ڵ��� : " + user_hp + "<br>";
		data += "�̸��� : " + user_email + "<br>";
		data += "�ּ� : " + user_address + "<br>";
		data += "</body></html>";
		out.print(data);
	}

}

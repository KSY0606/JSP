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


@WebServlet("/session1")
public class SessionTest1 extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.print("���� ���̵� : " + session.getId() + "<br>");
		out.print("���� ���� ���� �ð� : " + new Date(session.getCreationTime()) + "<br>");
		out.print("�ֱ� ���� ���� �ð� : " + new Date(session.getLastAccessedTime()) + "<br>");
		out.print("���� ��ȿ �ð� : " + session.getMaxInactiveInterval() + "<br>"); // �̼����� �⺻�� 30��
		if(session.isNew()) {
			out.print("�� ������ ����������ϴ�.");
		}
	}

}

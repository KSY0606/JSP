package jspetc.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/json")
public class JsonServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String jsonInfo = request.getParameter("jsonInfo");
		JSONParser jsonParser = new JSONParser();  // lib�� json-simple�� �߰��ؼ� ��� ����
		try {
			JSONObject jsonObject = (JSONObject)jsonParser.parse(jsonInfo);
			System.out.println("**ȸ������**");
			System.out.println("�̸� : " + jsonObject.get("name"));
			System.out.println("���� : " + jsonObject.get("age"));
			System.out.println("���� : " + jsonObject.get("job"));
		} catch (ParseException e) {
			System.out.println("JSON ��û ������ ����");
		}
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<p>������ ȸ�������� ���ۿϷ�</p>");
		out.print("</body></html>");
		
	}

}

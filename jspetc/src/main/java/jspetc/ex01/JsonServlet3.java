package jspetc.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/json3")
public class JsonServlet3 extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject gObject = new JSONObject();
		JSONArray memArray = new JSONArray();
		JSONObject memberInfo = new JSONObject();
		memberInfo.put("name", "ȫ�浿");
		memberInfo.put("age", "40");
		memberInfo.put("job", "����");
		memArray.add(memberInfo);
		memberInfo = new JSONObject();
		memberInfo.put("name", "��ö��");
		memberInfo.put("age", "34");
		memberInfo.put("job", "���");
		memArray.add(memberInfo);
		memberInfo = new JSONObject();
		memberInfo.put("name", "�̿���");
		memberInfo.put("age", "28");
		memberInfo.put("job", "�繫��");
		memArray.add(memberInfo);
		gObject.put("member", memArray);
		
		JSONArray bookArray = new JSONArray();
		JSONObject bookInfo = new JSONObject();
		bookInfo.put("title", "Node.js ���α׷���");
		bookInfo.put("writer", "�Ѻ��̵�� : ���μ�");
		bookInfo.put("image", "http://127.0.0.1:8090/jspetc/images/node.jpg");
		bookArray.add(bookInfo);
		bookInfo = new JSONObject();
		bookInfo.put("title", "�ǽð� ����� ���� ���α׷���");
		bookInfo.put("writer", "�Ѻ��̵�� : �׵�ε�");
		bookInfo.put("image", "http://127.0.0.1:8090/jspetc/images/ux.gif");
		bookArray.add(bookInfo);
		gObject.put("books", bookArray);
		String jsonInfo = gObject.toJSONString();
		out.print(jsonInfo);
	}

}

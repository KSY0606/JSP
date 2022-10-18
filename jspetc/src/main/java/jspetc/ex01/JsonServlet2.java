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


@WebServlet("/json2")
public class JsonServlet2 extends HttpServlet {
	
	
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
		memberInfo.put("name", "È«±æµ¿");
		memberInfo.put("age", "40");
		memberInfo.put("job", "µµÀû");
		memArray.add(memberInfo);
		memberInfo = new JSONObject();
		memberInfo.put("name", "±èÃ¶¼ö");
		memberInfo.put("age", "34");
		memberInfo.put("job", "»ç¿ø");
		memArray.add(memberInfo);
		memberInfo = new JSONObject();
		memberInfo.put("name", "ÀÌ¿µÈñ");
		memberInfo.put("age", "28");
		memberInfo.put("job", "»ç¹«¿ø");
		memArray.add(memberInfo);
		gObject.put("member", memArray);
		String jsonInfo = gObject.toJSONString();
		out.print(jsonInfo);
	}

}

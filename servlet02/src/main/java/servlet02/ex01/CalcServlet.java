package servlet02.ex01;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
	private static float USD_RATE = 1394.40F;
	private static float JPY_RATE = 965.07F;
	private static float CNY_RATE = 199.79F;
	private static float GBP_RATE = 1603.78F;
	private static float EUR_RATE = 1390.99F;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset = utf-8");
		PrintWriter pw = response.getWriter();
		String command = request.getParameter("command"); // 수행 할 요청
		String won = request.getParameter("won"); // 클라이언트의 입력값을 원화로 받음 (변환할 원화)
		String operator = request.getParameter("operator"); // 반환종류
		if(command != null && command.equals("calculate")) {
			String result = calculate(Float.parseFloat(won), operator);
			pw.print("<html><font size = 10>변환결과 </font><br>");
			pw.print("<font size = 10>" + result + "</font><br>");
			pw.print("<a href = '/servlet02/calc'>환율 계산기</a></html>");
			return;
		}
		pw.print("<html><title>환율 계산기</title>");
		pw.print("<body><font size = 5>환율계산기</font><br>");
		pw.print("<form name='frmCalc' method='get' action='/servlet02/calc'>");
		pw.print("원화 : <input type='text' name='won' size=10>");
		pw.print("<select name='operator'>");
		pw.print("<option value='dollar'>달러</option>");
		pw.print("<option value='en'>엔화</option>");
		pw.print("<option value='wian'>위안</option>");
		pw.print("<option value='pound'>파운드</option>");
		pw.print("<option value='euro'>유로</option>");
		pw.print("</select>");
		pw.print("<input type='hidden' name='command' value='calculate'>");
		pw.print("<input type='submit' value='변환'>");
		pw.print("</form>");
		pw.print("</body></html>");
		pw.close();
	}
	// 환율 계산의 결과값을 리턴해주는 메서드
	private static String calculate(float won, String operator) {
		String result = null;
		if(operator.equals("dollar")) {
			result = String.format("%.6f", won/USD_RATE);
		}else if(operator.equals("en")) {
			result = String.format("%.6f", won/JPY_RATE);
		}else if(operator.equals("wian")) {
			result = String.format("%.6f", won/CNY_RATE);
		}else if(operator.equals("pound")) {
			result = String.format("%.6f", won/GBP_RATE);
		}else if(operator.equals("euro")) {
			result = String.format("%.6f", won/EUR_RATE);
		}
		return result;
	}
}

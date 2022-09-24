package servletdb.ex05;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servletdb.ex05.SignUpVO;

@WebServlet("/viewSignUp")
public class ViewSignUpServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		List memberList = (List)request.getAttribute("memberList");
		out.print("<html><body>");
		out.print("<table border = 1>");
		out.print("<tr align = 'center' bgcolor = '#CEFBC9'>");
		out.print("<th>아이디</th><th>비밀번호</th><th>이름</th><th>생년월일</th><th>성별</th><th>전화번호</th><th>이메일</th>");
		out.print("</tr>");
		for(int i = 0; i < memberList.size(); i++) {
			SignUpVO memberVO = (SignUpVO)memberList.get(i);
			String id = memberVO.getId();
			String password = memberVO.getPassword();
			String name = memberVO.getName();
			String birth = memberVO.getBirth();
			String gender = memberVO.getGender();
			String tel = memberVO.getTel();
			String email = memberVO.getEmail();
			out.print("<tr><td>" + id + "</td><td>" + password + "</td><td>" + name + "</td><td>" + birth + "</td><td>" + gender + "</td><td>" + tel + "</td><td>" + email + "</td></tr>");
		}
		out.print("</table>");
		out.print("</body></html>");
	}
}

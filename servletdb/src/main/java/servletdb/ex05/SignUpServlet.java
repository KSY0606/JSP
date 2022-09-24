package servletdb.ex05;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servletdb.ex05.SignUpDAO;
import servletdb.ex05.SignUpVO;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		SignUpDAO dao = new SignUpDAO();
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		if(command != null && command.equals("addMember")) {
			String _id = request.getParameter("id");
			String _password = request.getParameter("password");
			String _name = request.getParameter("name");
			String _birth = request.getParameter("birth");
			String _gender = request.getParameter("gender");
			String _tel = request.getParameter("tel");
			String _email = request.getParameter("email");
			SignUpVO vo = new SignUpVO();
			vo.setId(_id);
			vo.setPassword(_password);
			vo.setName(_name);
			vo.setBirth(_birth);
			vo.setGender(_gender);
			vo.setTel(_tel);
			vo.setEmail(_email);
			dao.addMember(vo);
		}
		List memberList = dao.listMembers();
		request.setAttribute("memberList", memberList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewSignUp");
		dispatcher.forward(request, response);
	}
}

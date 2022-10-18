package memberMVC.ex01;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/mem.do")
public class MemberController extends HttpServlet {
	MemberDAO memberDAO;
	
	
	@Override
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		List<MemberVO> membersList = memberDAO.listMembers();  // DAO의 listMembers 가져와서 다시 담음
		request.setAttribute("membersList", membersList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/listMembers.jsp");
		dispatcher.forward(request, response);
	}

}

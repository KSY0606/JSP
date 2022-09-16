package servletdb.ex03;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	/*private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE"; // sql 계정 생성시 하단의 내용 (localhost, 1521, XE)
	private static final String user = "addaddress"; // sql 사용자계정 아이디, 비밀번호
	private static final String pwd = "9506";*/
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			// JNDI에 접근하기 위해 기본경로 ("java:/comp/env")를 지정
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			// 톰켓 context.xml에 설정한 name값인("jdbc/oracle")를 이용해 톰켓이 미리 연결한 DataSource를 받아온다.
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("커넥션풀 연결 실패");
		}
	}
	
	public List listMembers() {
		List list = new ArrayList();
		try {
			//connDB(); // 아래 connDB 호출
			con = dataFactory.getConnection();
			String query = "select * from memberstbl";
			pstmt=con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	/*private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url,user,pwd);
			System.out.println("Connection 생성 성공");
		}catch(Exception e) {
			System.out.println("연결오류 " + e.getMessage());
		}
	}*/
}

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
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE"; // sql ���� ������ �ϴ��� ���� (localhost, 1521, XE)
	private static final String user = "addaddress"; // sql ����ڰ��� ���̵�, ��й�ȣ
	private static final String pwd = "9506";*/
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("Ŀ�ؼ�Ǯ ���� ����");
		}
	}
	
	public List listMembers() {
		List list = new ArrayList();
		try {
			//connDB(); // �Ʒ� connDB ȣ��
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
			System.out.println("Oracle ����̹� �ε� ����");
			con = DriverManager.getConnection(url,user,pwd);
			System.out.println("Connection ���� ����");
		}catch(Exception e) {
			System.out.println("������� " + e.getMessage());
		}
	}*/
}

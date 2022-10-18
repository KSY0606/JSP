package memberMVC.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory; // server�� context ����
	private Connection conn;
	private PreparedStatement pstmt; // ������ ������ �����ϴ� �۾��� ���ϰ� ����
	
	// db �����۾�   �⺻������ try catch�������  ������ ���񽺿��� listener �� �����ֳ� Ȯ��
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			System.out.println("DB ���� ����");
		}
	}
	
	// ȸ�� ��� 
	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from memberstbl by joinDate desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
				membersList.add(memberVO);
			}
			rs.close();
			pstmt.clearBatch();
			conn.close();
		} catch (Exception e) {
			System.out.println("DB ��ȸ �� ����");
		}
		return membersList;
	}
	
	// ȸ�� ���
	public void addMember(MemberVO mem) {
		try {
			conn = dataFactory.getConnection();
			String id = mem.getId();
			String pwd = mem.getPwd();
			String name = mem.getName();
			String email = mem.getEmail();
			String query = "insert into memberstbl (id, pwd, name, email) values(?,?,?,?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			pstmt.setString(3,name);
			pstmt.setString(4,email);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DB ��� �� ����");
		}
	}
}

package jspAction01.ex01;

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
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext=(Context)ctx.lookup("java:comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("����Ŭ ���� �ȵ�");
		}
	}
	
	 // ȸ�� ��� Ȯ��
	public List listMembers() {
		List list = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "select * from memberstbl";
			System.out.println("��ɹ� ������ : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberBean vo = new MemberBean();
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
		} catch(Exception e) {
			System.out.println("����Ŭ ������ ���������ϴ�.");
		}
		return list;
	}
	// ȸ�� ��� 
	public void addMember(MemberBean memberBean) {
		try {
			con = dataFactory.getConnection(); // �����ͺ��̽� ����
			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			//String query = "insert into memberstbl(id,pwd,name,email) vlaues('" + id + "','" + pwd + "','" + name + "','" + email + "')";
			String query = "insert into memberstbl(id,pwd,name,email) values(?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate(); // 73~76 ������ 71 values�� ?�� �־���   ��� �۾� ����
			pstmt.close();
		}catch(Exception e) {
			System.out.println("����� �����߻�");
		}
	}
	// ȸ�� ����
	public void delMember(String id) {
		try {
			con = dataFactory.getConnection();
			String query = "delete from memberstbl where id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate(); // ���� ����
			pstmt.close();
		} catch(Exception e) {
			System.out.println("������ �����߻�");
		}
	}
}


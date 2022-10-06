package exam.ex01;


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
	
	 // �Խñ� ��� Ȯ��
	public List read() {
		List list = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "select * from examdb";
			System.out.println("��ɹ� ������ : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String num = rs.getString("num");
				String name = rs.getString("name");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String pass = rs.getString("pass");
				String count = rs.getString("count");
				Date regdate = rs.getDate("regdate");
				MemberBean vo = new MemberBean();
				vo.setNum(num);
				vo.setName(name);
				vo.setSubject(subject);
				vo.setText(text);
				vo.setPass(pass);
				vo.setCount(count);
				vo.setRegdate(regdate);
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
	// �Խñ� ��� 
	public void addMember(MemberBean memberBean) {
		try {
			con = dataFactory.getConnection();
			String name = memberBean.getName();
			String subject = memberBean.getSubject();
			String text = memberBean.getText();
			String pass = memberBean.getPass();
			String query = "insert into examdb(name, subject, text, pass) values(?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			pstmt.setString(3, text);
			pstmt.setString(4, pass);
			pstmt.executeUpdate();
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


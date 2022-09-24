package servletlink.ex05;

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

import servletlink.ex05.MemberVO;

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
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from logintest";
			System.out.println("��ɹ� ������ : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
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
	public void addMember(MemberVO memVo) {
		try {
			con = dataFactory.getConnection();
			String id = memVo.getId();
			String pwd = memVo.getPwd();
			String query = "insert into logintest(id,pwd) values(?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			System.out.println("����� �����߻�");
		}
	}
	// ȸ������ Ȯ��
	public boolean isExisted(MemberVO memberVO) {
		boolean result = false;
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		try {
			con = dataFactory.getConnection();
			String query = "select decode(count(*), 1, 'true', 'false') as result from logintest where id=? and pwd=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result")); 
		} catch(Exception e) {
			System.out.println("����Ŭ�� ������ ���������ϴ�.");
		}
		return result;
	}
}


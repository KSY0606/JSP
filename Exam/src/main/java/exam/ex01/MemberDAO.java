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
			System.out.println("오라클 연결 안됨");
		}
	}
	
	 // 게시글 목록 확인
	public List read() {
		List list = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "select * from examdb";
			System.out.println("명령문 수행결과 : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = Integer.parseInt(rs.getString("num"));
				String name = rs.getString("name");
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				String pass = rs.getString("pass");
				int count = Integer.parseInt(rs.getString("count"));
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
			System.out.println("오라클 연결이 끊어졌습니다.");
		}
		return list;
	}
	// 게시글 등록 
	public void addMember(MemberBean memberBean) {
		try {
			con = dataFactory.getConnection();
			int num = memberBean.getNum();
			String name = memberBean.getName();
			String subject = memberBean.getSubject();
			String text = memberBean.getText();
			String pass = memberBean.getPass();
			int count = memberBean.getCount();
			String query = "insert into examdb(num, name, subject, text, pass, count) values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.setString(2, name);
			pstmt.setString(3, subject);
			pstmt.setString(4, text);
			pstmt.setString(5, pass);
			pstmt.setInt(6, count);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			System.out.println("등록중 오류발생");
		}
	}
	// 회원 삭제
	public void delMember(String id) {
		try {
			con = dataFactory.getConnection();
			String query = "delete from memberstbl where id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate(); // 삭제 실행
			pstmt.close();
		} catch(Exception e) {
			System.out.println("삭제중 오류발생");
		}
	}
}


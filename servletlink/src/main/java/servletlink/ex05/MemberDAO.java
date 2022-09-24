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
			System.out.println("오라클 연결 안됨");
		}
	}
	
	 // 회원 목록 확인
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from logintest";
			System.out.println("명령문 수행결과 : " + query);
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
			System.out.println("오라클 연결이 끊어졌습니다.");
		}
		return list;
	}
	// 회원 등록 
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
			System.out.println("등록중 오류발생");
		}
	}
	// 회원가입 확인
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
			System.out.println("오라클과 연결이 끊어졌습니다.");
		}
		return result;
	}
}


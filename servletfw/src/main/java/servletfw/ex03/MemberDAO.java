package servletfw.ex03;

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

import servletfw.ex03.MemberVO;

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
			String query = "select * from memberstbl";
			System.out.println("명령문 수행결과 : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
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
		} catch(Exception e) {
			System.out.println("오라클 연결이 끊어졌습니다.");
		}
		return list;
	}
	// 회원 등록 
	public void addMember(MemberVO memVo) {
		try {
			con = dataFactory.getConnection(); // 데이터베이스 연결
			String id = memVo.getId();
			String pwd = memVo.getPwd();
			String name = memVo.getName();
			String email = memVo.getEmail();
			//String query = "insert into memberstbl(id,pwd,name,email) vlaues('" + id + "','" + pwd + "','" + name + "','" + email + "')";
			String query = "insert into memberstbl(id,pwd,name,email) values(?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate(); // 73~76 정보를 71 values의 ?에 넣어줌   등록 작업 실행
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


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
	private DataSource dataFactory; // server의 context 설정
	private Connection conn;
	private PreparedStatement pstmt; // 쿼리문 조건을 연결하는 작업을 편하게 해줌
	
	// db 연결작업   기본적으로 try catch해줘야함  오류시 서비스에서 listener 등 꺼져있나 확인
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
	}
	
	// 회원 목록 
	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from memberstbl order by joinDate desc";
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
			System.out.println("DB 조회 중 에러");
		}
		return membersList;
	}
	
	// 회원 등록
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
			System.out.println("DB 등록 중 에러");
		}
	}
	
	// 수정 할 회원정보 찾기
	public MemberVO findMember(String _id) {
		MemberVO memFindInfo = null;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from memberstbl where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, _id);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate");
			memFindInfo = new MemberVO(id, pwd, name, email, joinDate);
			pstmt.close();
			conn.close();
			rs.close();
		}catch (Exception e) {
			System.out.println("수정할 자료 찾는중 에러");
		}
		return memFindInfo;
	}
	
	// 회원 정보 수정
	public void modMember(MemberVO memberVO) {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		try {
			conn = dataFactory.getConnection();
			String query = "update memberstbl set pwd=?, name=?, email=? where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("회원정보 수정 중 에러");
		}
	}
	
	// 회원 삭제
	public void delMember(String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from memberstbl where id = ?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e) {
			System.out.println("삭제중 오류발생");
		}
	}
}

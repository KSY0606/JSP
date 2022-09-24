package servletdb.ex05;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import servletdb.ex05.SignUpVO;

public class SignUpDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public SignUpDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext=(Context)ctx.lookup("java:comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("오라클 연결 안됨");
		}
	}
	
	 // 회원 목록 확인
	public List<SignUpVO> listMembers() {
		List<SignUpVO> list = new ArrayList<SignUpVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from signup";
			System.out.println("명령문 수행결과 : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String birth = rs.getString("birth");
				String gender = rs.getString("gender");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				SignUpVO vo = new SignUpVO();
				vo.setId(id);
				vo.setPassword(password);
				vo.setName(name);
				vo.setBirth(birth);
				vo.setGender(gender);
				vo.setTel(tel);
				vo.setEmail(email);
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
	public void addMember(SignUpVO signVo) {
		try {
			con = dataFactory.getConnection();
			String id = signVo.getId();
			String password = signVo.getPassword();
			String name = signVo.getName();
			String birth = signVo.getBirth();
			String gender = signVo.getGender();
			String tel = signVo.getTel();
			String email = signVo.getEmail();
			String query = "insert into signup(id,password,name,birth,gender,tel,email) values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, gender);
			pstmt.setString(6, tel);
			pstmt.setString(7, email);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			System.out.println("등록중 오류발생");
		}
	}
}
package memberMVC.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	// db 연결작업   기본적으로 try catch해줘야함  오류시 서비스에서 listener 등 꺼져있나 확인
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
	}
	
	// 글 목록 메서드
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articleList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select LEVEL, articleNo, parentNo, title, content, id, writeDate from boardtbl START WITH parentNo = 0 CONNECT BY PRIOR " +
					"articleNo = parentNo ORDER SIBLINGS BY articleNo DESC";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level"); // 각 글의 깊이(계층) level 속성을 지정
				int articleNo = rs.getInt("articleNo");
				int parentNo = rs.getInt("parentNo");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNo(articleNo);
				article.setParentNo(parentNo);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articleList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("글 목록 조회중 에러");
		}
		return articleList;
	}
	
	// 글 번호 생성 메서드
	
	private int getNewArticleNo() {
		try {
			conn=dataFactory.getConnection();
			String query = "select max(articleNo) from boardtbl";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if(rs.next()) {
				return (rs.getInt(1)+1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("글 번호 생성중 에러");
		}
		return 0;
	}
	
	// 새 글 추가 메서드
	public int insertNewArticle(ArticleVO article) {
		int articleNo = getNewArticleNo();
		try {
			conn=dataFactory.getConnection();
			int parentNo = article.getParentNo();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String query="insert into boardtbl(articleNo, parentNo, title, content, imageName, id) values(?,?,?,?,?,?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			pstmt.setInt(2, parentNo);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("새 글 추가중 에러");
		}
		return articleNo;
	}
}
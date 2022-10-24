package memberMVC.ex02;

import java.net.URLEncoder;
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
	
	// 글 전체 목록 메서드
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articleList = new ArrayList();
		try {
			conn = dataFactory.getConnection(); 
			// 오라클 계층형 SQL문을 실행
			String query = "select LEVEL, articleNo, parentNo, title, content, id, writeDate from boardtbl START WITH parentNo = 0 CONNECT BY PRIOR " +
					"articleNo = parentNo ORDER SIBLINGS BY articleNo DESC";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level"); // 각 글의 깊이(계층) level 속성을 지정
				int articleNo = rs.getInt("articleNo"); // 글 번호는 숫자형(getInt)
				int parentNo = rs.getInt("parentNo"); 
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO(); // 글 정보를 ArticleVO에 설정
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
			// 기본 글 번호 중 max함수를 이용해 가장 큰 번호를 조회
			String query = "select max(articleNo) from boardtbl";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			// 가장 큰 번호에 1을 더한 번호 저장
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
		int articleNo = getNewArticleNo(); // 새 글을 추가하기 전에 새글에 대한 글번호를 가져옴
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
	
	// 선택된 글 상세 내용
	public ArticleVO selectArticle(int articleNo) {
		ArticleVO article = new ArticleVO();
		try {
			conn=dataFactory.getConnection();							// 이미지가 있으면 이미지 이름을,  없으면 null이라는 문자를 전달
			String query = "select articleNo, parentNo, title, content, NVL(imageFileName,'null') as imageFileName, id, writeDate from boardtbl where articleNo=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _articleNo = rs.getInt("articleNo");
			int parentNo = rs.getInt("parentNo");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String imageFileName =URLEncoder.encode(rs.getString("imageFileName"),"utf-8");
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
		
			// ArticleVO에 저장
			article.setArticleNo(_articleNo);
			article.setParentNo(parentNo);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("글 상세 구현 중 에러");
		}
		return article;
	}
}
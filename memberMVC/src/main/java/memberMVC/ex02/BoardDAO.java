package memberMVC.ex02;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	// db �����۾�   �⺻������ try catch�������  ������ ���񽺿��� listener �� �����ֳ� Ȯ��
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			System.out.println("DB ���� ����");
		}
	}
	
	// �� ��� ����¡ �޼���
	public List selectAllArticles(Map<String, Integer> pagingMap) {
		List<ArticleVO> articleList = new ArrayList<ArticleVO>();
		int section = (Integer)pagingMap.get("section");
		int pageNum = (Integer)pagingMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM (SELECT ROWNUM AS recNum, LVL, articleNo, parentNo, title, id, writeDate from (SELECT LEVEL AS LVL, articleNo, " +
			"parentNo, title, id, writeDate from boardtbl START WITH parentNo = 0 CONNECT BY PRIOR articleNo = parentNo ORDER SIBLINGS BY articleNo DESC)) " +
			"WHERE recNum BETWEEN(?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("lvl");
				int articleNo = rs.getInt("articleNo");
				int parentNo = rs.getInt("parentNo");
				String title = rs.getString("title");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNo(articleNo);
				article.setParentNo(parentNo);
				article.setTitle(title);
				article.setId(id);
				article.setWriteDate(writeDate);
				articleList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("�� ��� ����¡ ��ȸ�� ����");
		}
		return articleList;
	}
	
	// �� ��ü ��� �޼���
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articleList = new ArrayList();
		try {
			conn = dataFactory.getConnection(); 
			// ����Ŭ ������ SQL���� ����
			String query = "select LEVEL, articleNo, parentNo, title, content, id, writeDate from boardtbl START WITH parentNo = 0 CONNECT BY PRIOR " +
					"articleNo = parentNo ORDER SIBLINGS BY articleNo DESC";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int level = rs.getInt("level"); // �� ���� ����(����) level �Ӽ��� ����
				int articleNo = rs.getInt("articleNo"); // �� ��ȣ�� ������(getInt)
				int parentNo = rs.getInt("parentNo"); 
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO(); // �� ������ ArticleVO�� ����
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
			System.out.println("�� ��� ��ȸ�� ����");
		}
		return articleList;
	}
	
	// �� ��ȣ ���� �޼���
	
	private int getNewArticleNo() {
		try {
			conn=dataFactory.getConnection();
			// �⺻ �� ��ȣ �� max�Լ��� �̿��� ���� ū ��ȣ�� ��ȸ
			String query = "select max(articleNo) from boardtbl";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			// ���� ū ��ȣ�� 1�� ���� ��ȣ ����
			if(rs.next()) {
				return (rs.getInt(1)+1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("�� ��ȣ ������ ����");
		}
		return 0;
	}
	
	// �� �� �߰� �޼���
	public int insertNewArticle(ArticleVO article) {
		int articleNo = getNewArticleNo(); // �� ���� �߰��ϱ� ���� ���ۿ� ���� �۹�ȣ�� ������
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
			System.out.println("�� �� �߰��� ����");
		}
		return articleNo;
	}
	
	// ���õ� �� �� ����
	public ArticleVO selectArticle(int articleNo) {
		ArticleVO article = new ArticleVO();
		try {
			conn=dataFactory.getConnection();							// �̹����� ������ �̹��� �̸���,  ������ null�̶�� ���ڸ� ����
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
			if(imageFileName.equals("null")){
				imageFileName = null;
			}
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
		
			// ArticleVO�� ����
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
			System.out.println("�� �� ���� �� ����");
		}
		return article;
	}
	
	// �� �����ϱ�
	public void updateArticle(ArticleVO article) {
		int articleNo = article.getArticleNo();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		try {
			conn=dataFactory.getConnection();
			String query = "update boardtbl set title=?, content=?";
			if(imageFileName != null && imageFileName.length() != 0) {
				query += ", imageFileName=?";
			}
			query += " where articleNo=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			if(imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNo);
			}else {
				pstmt.setInt(3, articleNo);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("DB ���� �� ����");
		}
	}
	
	// ���� �� �� ��ȣ ��ϰ������� 
	public List<Integer> selectRemoveArticles(int articleNo) {
		List<Integer> articleNoList = new ArrayList<Integer>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT articleNo FROM boardtbl START WITH articleNo=?";
			query += " CONNECT BY PRIOR articleNo = parentNo";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				articleNo = rs.getInt("articleNo");
				articleNoList.add(articleNo);
			}
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("�� ��ȣ ó�� �� ����");
		}
		return articleNoList;
	}
	
	// �� ����
	public void deleteArticle(int articleNo) {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM boardtbl WHERE articleNo in (SELECT articleNo FROM boardtbl START WITH articleNo=? CONNECT BY PRIOR articleNo = parentNo";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("�� ���� ó�� �� ����");
		}
	}
	
	// ��ü �� ��� ��
	public int selectToArticles() {
		try {
			String query = "select count(articleNo) from boardtbl";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next() ) {
				return (rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("�� ��� �� ó�� �� ����");
		}
		return 0;
	}
}
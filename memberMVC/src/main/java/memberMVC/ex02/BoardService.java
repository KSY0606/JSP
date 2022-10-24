package memberMVC.ex02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO(); // 积己磊俊辑 BoardDAO按眉甫 积己
	}
	
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articleList = boardDAO.selectAllArticles();
		return articleList;
	}
	
	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}
	
	public ArticleVO viewArticle(int articleNo) {
		ArticleVO article = null;
		article = boardDAO.selectArticle(articleNo);
		return article;
	}
}

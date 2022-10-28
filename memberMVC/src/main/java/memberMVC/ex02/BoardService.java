package memberMVC.ex02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO(); // 积己磊俊辑 BoardDAO按眉甫 积己
	}
	
	public Map listArticles(Map<String, Integer> pagingMap) {
		Map articleMap = new HashMap();
		List<ArticleVO> articleList = boardDAO.selectAllArticles(pagingMap);
		int totArticles = boardDAO.selectToArticles();
		articleMap.put("articleList", articleList);
		articleMap.put("totArticles", articleList);
		return articleMap;
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
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}
	
	public List<Integer> removeArticle(int articleNo) {
		List<Integer> articleNoList = boardDAO.selectRemoveArticles(articleNo);
		boardDAO.deleteArticle(articleNo);
		return articleNoList;
	}
	
	public int addReply(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}
}

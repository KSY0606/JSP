package memberMVC.ex02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articleList = boardDAO.selectAllArticles();
		return articleList;
	}
}

package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.connection.ConnectionProvider;

public class SearchArticleService {
	
	private ArticleDao articeDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public ArticleData getArticle(String articleStr, boolean increseReadCount) {
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			Article article = ArticleDao.selectBylocalName(conn, articleStr);
		} catch (SQLException e) {
			throw new RuntimeException (e);
		}
		
	}
	
	
}

package article.service;
import java.sql.Connection;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.connection.ConnectionProvider;

public class DeleteArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();

	
	public void delete(DeleteArticleRequest deleteReq) throws Exception {
		Connection conn = null;
		conn = ConnectionProvider.getConnection();
		Article article = articleDao.selectById(conn, deleteReq.getArticleNumber());
				if(article == null) {
					throw new ArticleNotFoundException();
				}
				articleDao.delete(conn, article.getNumber());
				contentDao.delete(conn, article.getNumber());
				
				
			}
		}
	

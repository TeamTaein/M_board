package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.model.Article;
import comment.dao.CommentDao;
import comment.model.Comment;
import jdbc.connection.ConnectionProvider;

public class ListCommentService {
	private CommentDao commentDao = new CommentDao();
	private int size = 10;
	
	public CommentPage getCommentPage(Integer articleNum ,int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			int total = commentDao.selectCount(conn, articleNum);
			List<Comment> content = CommentDao.select(conn,articleNum,(pageNum-1)*size,size);
			return new CommentPage(articleNum,total, pageNum, size, content);
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

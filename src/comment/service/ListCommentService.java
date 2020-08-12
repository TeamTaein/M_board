package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
	//20200812 11:10 여기서부터 널값
	public String getCommentWriter(Integer articleNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			String commentWriter = CommentDao.selectByArNo(conn, articleNum).getCommentWriter().getName();
			System.out.println(commentWriter);
			return commentWriter;
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	public Comment regdateContent(Integer articleNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Comment comment = CommentDao.selectByArNo(conn, articleNum);
			return comment;
		}catch(SQLException exc){
			exc.printStackTrace();
			throw new RuntimeException(exc);
		}
		
	}

}

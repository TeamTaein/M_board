package comment.service;

import java.sql.Connection;

import comment.dao.CommentDao;
import comment.model.Comment;
import jdbc.connection.ConnectionProvider;

public class DeleteCommentService {
	private CommentDao commentDao = new CommentDao();
	
	public void delete(DeleteCommentRequest deleteReq) throws Exception{
		Connection conn = ConnectionProvider.getConnection();
		Comment comment = commentDao.selectByNo(conn, deleteReq.getCommentNo());
		if(comment == null) {
			throw new CommentNotFoundException();
		}
		commentDao.delete(conn, comment.getCmtNum());
		
	}
}

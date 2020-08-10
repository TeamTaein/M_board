package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import comment.dao.CommentDao;
import comment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteCommentService {
	private CommentDao commentDao = new CommentDao();
	
	public Integer write(WriteCommentRequest req) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			//모델로부터  comment 객체 생성
			//쿼리 실행후 댓글번호 확인 가능하므로  일단 null 값줌
			Comment comment = toComment(req);
			System.out.println(comment.getArticleNum()+"서비스에서 게시글 번호 받음");
			//Commentdao의 insert 메서드 실행후 그 결과를  savedComment에 전달
			// 이제 db의 댓글 번호값 가짐
			System.out.println("이게 마지막이면 dao 문제");
			Comment savedComment = commentDao.insert(conn, comment);
			if(savedComment == null) {
				throw new RuntimeException("fail to insert comment");
			}
			conn.commit();
			System.out.println(savedComment.getCommentNo()+"서비스에서 댓글번호 받음");
			return savedComment.getCommentNo();
			//새로 추가한 게시글 번호 리턴
			
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
			
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	private Comment toComment(WriteCommentRequest req) {
		Date now  = new Date();
		return new Comment(req.getArticleNum(),req.getCommentWriter(),null,req.getCommentContent(),now);
	}
	
	
	
}

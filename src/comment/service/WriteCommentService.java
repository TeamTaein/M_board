package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import comment.dao.CommentDao;
import comment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteCommentService {
	
	private static CommentDao commentDao = new CommentDao();
	
	public Integer write(WriteCommentRequest req) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			// 트랜잭션 시작
			conn.setAutoCommit(false);
		
			//WriteCommentRequest로 부터 Comment 객체 생성
			//toComment에서 Comment 객체를 생성할 때 number 값으로 null 전달
			Comment comment = toComment(req);
			System.out.println(comment.getNumber()+"서비스에서 게시글 번호 받음");
			//CommentDao의 insert메서드를 실행하고 그 결과를 saveComment에 할당
			//데이터를 삽입한 경우 null이 아니고, article_comment 테이블에 추가한 데이터의 주요 키값을 number로 갖는다
			Comment saveComment = commentDao.insert(conn, comment);
		
	
		if(saveComment == null) {
			throw new RuntimeException("fail to insert comment");
		}
		
		// 트래잭션커밋
		conn.commit();
		//새로 추가한 댓글번호  리턴
		System.out.println(saveComment.getCmtNum()+"서비스에서 댓글번호 받음");
		return saveComment.getCmtNum(); 

				
		
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}		
	}

	private static Comment toComment(WriteCommentRequest req) {
		Date now = new Date();
		return new Comment(null, req.getArticleNum(),req.getCommentWriter(),now,req.getCommentContent());
	}

}

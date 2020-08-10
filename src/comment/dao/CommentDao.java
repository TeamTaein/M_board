package comment.dao;

import java.sql.*;

import java.util.Date;
import comment.model.Comment;
import jdbc.JdbcUtil;

public class CommentDao {
	
	
	 public Comment insert(Connection conn, Comment comment) throws SQLException {
		 
		
		 Statement stmt = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 
		 try {
			 //insert 쿼리 실행 테이블 데이터 삽입
			 // 댓글번호는 자동증가 컬럼, article_no는 세션에서 정보 가져오기,댓글 id는 로그인세션에서 정보 가져오기
			 pstmt = conn.prepareStatement("insert into article_comment "
		                +"(article_no,comment_id,comment_content, comment_regdate ) "
		                +"values (?,?,?,?)");
		          pstmt.setInt(1, comment.getArticleNum());
		          System.out.println(1+"setArticleNum");
		          pstmt.setString(2,comment.getCommentWriter().getName());
		          System.out.println(2+"setCommentWriter");
		          pstmt.setString(3,comment.getCommentContent());
		          System.out.println(3+"setCommentContent");
		          pstmt.setTimestamp(4,toTimestamp(comment.getRegDate()));
		          System.out.println(4+"setRegDate");
		          int insertedCount = pstmt.executeUpdate();
		          System.out.println("insertedCount 구하기");
		          if(insertedCount > 0) {
		        	  stmt = conn.createStatement();
		        	  rs = stmt.executeQuery("select last_insert_id() from article_comment");
		        	  if(rs.next()) {
		        		  Integer newNum = rs.getInt(1);
		        		  return new Comment(
		        				  comment.getArticleNum(),
		        				  comment.getCommentWriter(),
		        				  newNum,
		        				  comment.getCommentContent(),
		        				  comment.getRegDate() 
		        				  );
		        	  }
		          }
		          return null;
			 
		 } finally {
			 JdbcUtil.close(rs,stmt,pstmt);
		 }
	 }
	 private Timestamp toTimestamp(Date date) {
		 return new Timestamp(date.getTime());
	 }

	 
}

package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comment.model.Comment;
import comment.model.CommentWriter;
import jdbc.JdbcUtil;

public class CommentDao {
	private static CommentDao CommentDao = new CommentDao();

	public static CommentDao getInstance() {
		return CommentDao;
	}
	
	 public Comment insert(Connection conn, Comment comment) throws SQLException {
		 
		 PreparedStatement pstmt = null;
		 Statement stmt = null;
		 ResultSet rs = null;
		 
		 try {
			 //comment_no 컬럼은 자동증가 컬럼이므로 값 지정하지 않음
			 pstmt = conn.prepareStatement("insert into article_comment "
		                +"(article_no, comment_id, comment_Regdate, comment_content) "
		                +"values (?,?,?,?)");
		          pstmt.setLong(1,comment.getNumber());		         
		          pstmt.setString(2,comment.getCommentWriter().getName());
		          pstmt.setTimestamp(3,toTimestamp(comment.getCmtRegdate()));		         
		          pstmt.setString(4,comment.getCmtContent());
		          
		          int insertedCount = pstmt.executeUpdate();
		          
		          if(insertedCount > 0) {
		        	  stmt = conn.createStatement();
		        	  
		        	  // comment_no의 값을 구함
		        	  rs = stmt.executeQuery("select last_insert_id() from article_comment");
		        	  
		        	  if(rs.next()) {
		        		  // 신규 댓글의 번호를 구함
		        		  Integer newCmtNum = rs.getInt(1);
		        		  //comment 테이블에 추가한 데이터를 담은 Comment 객체 리턴
		        		  return new Comment(newCmtNum,
		        				  comment.getNumber(),
		        				  comment.getCommentWriter(),
		        				  comment.getCmtRegdate(),
		        				  comment.getCmtContent());
		        	  }
		          }
		          
		         return null;
			 
		 } finally {
			 JdbcUtil.close(pstmt, rs, stmt);
		 }
	 }

	 
	 
	private Timestamp toTimestamp(Date cmtRegdate) {
		return new Timestamp(cmtRegdate.getTime());
	}
	
	
	public int selectCount(Connection conn, Integer articleNum) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select count(*) from article_comment "
											+ "where article_no = ?");
			pstmt.setInt(1, articleNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	public static List<Comment> select(Connection conn, Integer articleNum, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from article_comment "
										+ "where article_no=? "
										+ "order by comment_no desc limit ?,?");
			pstmt.setInt(1, articleNum);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			
			List<Comment> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertArticle(rs));				
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		
	}

	private static Comment convertArticle(ResultSet rs) throws SQLException {
		
		return new Comment(rs.getInt("comment_no"),
							rs.getInt("article_no"),
					new CommentWriter(rs.getString("comment_id")),					
					toDate(rs.getTimestamp("comment_regdate")),
					rs.getString("comment_content"));
	}

	private static Date toDate(Timestamp timestamp) {
		// TODO Auto-generated method stub
		return new Date(timestamp.getTime());
	}
	
	public static Comment selectByNo(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from article_comment where comment_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			Comment comment =null;
			if(rs.next()) {
				comment = convertArticle(rs);
			}
			return comment;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	public static Comment selectByArNo(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from article_comment where article_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Comment comment = null;
			if(rs.next()) {
				comment = convertArticle(rs);
			}
			return comment;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	public int delete(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("delete from article_comment where comment_no=?");
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	
}















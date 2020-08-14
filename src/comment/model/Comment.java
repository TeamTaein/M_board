
package comment.model;

import java.util.Date;

/**
 * @author xodls
 *
 */
public class Comment {
		private Integer articleNum; // 글번호
		private CommentWriter commentWriter; //작성자
	    private Integer commentNo;        // 댓글 글번호     
	    private String commentContent;      // 댓글 내용
	    private Date regDate;   // 댓글 작성일
		public Integer getArticleNum() {
			return articleNum;
		}
		public CommentWriter getCommentWriter() {
			return commentWriter;
		}
		public Integer getCommentNo() {
			return commentNo;
		}
		public String getCommentContent() {
			return commentContent;
		}
		public Date getRegDate() {
			return regDate;
		}
		public Comment(Integer articleNum, CommentWriter commentWriter, Integer commentNo,  String commentContent,
				Date regDate) {
			super();
			this.articleNum = articleNum;
			this.commentWriter = commentWriter;
			this.commentNo = commentNo;
			this.commentContent = commentContent;
			this.regDate = regDate;
		}
	   

		
		
		
		}




package comment.model;

import java.util.Date;

public class Comment {
	
	    private Integer cmtNum;        // 댓글 글번호
	    private Integer number;    // 게시글 번호
	    private CommentWriter commentWriter;      // 댓글 작성자
	    private Date cmtRegdate;   // 댓글 작성일	
	    private String cmtContent; // 댓글 내용   	  
	    
	    public Comment(Integer cmtNum,Integer number, CommentWriter commentWriter, Date cmtRegdate, String cmtContent) {
	    		   
	    	this.cmtNum = cmtNum;
	    	this.number = number;
	    	this.commentWriter = commentWriter;
	    	this.cmtRegdate = cmtRegdate;
	    	this.cmtContent = cmtContent;
	    }

		public int getCmtNum() {
			return cmtNum;
		}

		public Integer getNumber() {
			return number;
		}

		public CommentWriter getCommentWriter() {
			return commentWriter;
		}

		public Date getCmtRegdate() {
			return cmtRegdate;
		}

		public String getCmtContent() {
			return cmtContent;
		}	    
	    


	    
	    
	    
	   
		
	    
	    
	    
	    
	    
		
		
	    
	    

}

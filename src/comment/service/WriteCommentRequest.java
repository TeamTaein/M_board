package comment.service;

import java.util.Map;


import comment.model.CommentWriter;

public class WriteCommentRequest {
	private Integer articleNum;
	private CommentWriter commentWriter;
	private String commentContent;
	
	public Integer getArticleNum() {
		return articleNum;
	}
	public CommentWriter getCommentWriter() {
		return commentWriter;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public WriteCommentRequest(Integer articleNum, CommentWriter commentWriter, String commentContent) {
		super();
		this.articleNum = articleNum;
		this.commentWriter = commentWriter;
		this.commentContent = commentContent;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(commentContent==null || commentContent.trim().isEmpty()) {
			errors.put("commentContent",Boolean.TRUE);
		}
	

}
}
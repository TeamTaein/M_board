package comment.service;

import comment.model.Comment;

public class CommentData {
	private Comment comment;
	
	public CommentData(Comment comment) {
		super();
		this.comment = comment;
	}

	public Comment getComment() {
		return comment;
	}
	
	
}

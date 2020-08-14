
package comment.service;

import java.util.Map;

public class DeleteCommentRequest {
	private int commentNo;
	
	int getCommentNo() {
		return commentNo;
	}
	public DeleteCommentRequest(int commentNo) {
		super();
		this.commentNo = commentNo;
	}
	public void validate(Map<String ,Boolean> errors) {
		if(commentNo ==0) {
			errors.put("commentNo", Boolean.TRUE);
		}
	}

}


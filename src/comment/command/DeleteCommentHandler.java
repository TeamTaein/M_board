
package comment.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.service.DeleteCommentRequest;
import comment.service.DeleteCommentService;
import mvc.controller.CommandHandler;

public class DeleteCommentHandler implements CommandHandler {
	private DeleteCommentService deleteService = new DeleteCommentService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String numTemp = req.getParameter("comment_no");

		int commentNo = Integer.parseInt(numTemp);
		DeleteCommentRequest deleteReq = new DeleteCommentRequest(commentNo);
		req.setAttribute("deleteReq", deleteReq);
		
		Map<String, Boolean>errors = new HashMap<>();
		req.setAttribute("errors", errors);
		deleteReq.validate(errors);
		
		if(errors.isEmpty()) {
			String listPageNo = session.getAttribute("listNo").toString();
		
			Integer ArticleNum = (Integer)session.getAttribute("NumForComment");
		
		try {
			deleteService.delete(deleteReq);
			res.sendRedirect("read.do?no="+ArticleNum+"&pageNo="+listPageNo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		}return null;
		}
	

}


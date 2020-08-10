package comment.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.DeleteCommentRequest;
import comment.service.DeleteCommentService;
import mvc.controller.CommandHandler;

public class DeleteCommentHandler implements CommandHandler {
	private DeleteCommentService deleteService = new DeleteCommentService();
	private static final String FORM_VIEW = "/WEB-INF/view/readArticle.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String numTemp = req.getParameter("comment_no");
		int commentNo = Integer.parseInt(numTemp);
		DeleteCommentRequest deleteReq = new DeleteCommentRequest(commentNo);
		req.setAttribute("deleteReq", deleteReq);
		
		Map<String, Boolean>errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		deleteReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return "/WEB-INF/view/readArticle.jsp";
		}try {
			deleteService.delete(deleteReq);
			return "WEB-INF/view/readArticle.jsp";
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

}

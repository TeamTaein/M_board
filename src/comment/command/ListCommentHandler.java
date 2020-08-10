package comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.service.CommentPage;
import comment.service.ListCommentService;
import mvc.controller.CommandHandler;

public class ListCommentHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "WEB/view/readArticle.jsp";
	private ListCommentService listservice = new ListCommentService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse response) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo =1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}	
			HttpSession session = req.getSession();
			Integer ArticleNum = (Integer)session.getAttribute("NumforComment");
			CommentPage commentPage = listservice.getCommentPage(ArticleNum,pageNo);
			req.setAttribute("CommentPage", commentPage);
			return FORM_VIEW;
		
		
	}
	
}

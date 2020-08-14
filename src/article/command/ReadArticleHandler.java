package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.NumForComment;
import article.service.ReadArticleService;
import comment.service.CommentPage;
import comment.service.ListCommentService;
import mvc.controller.CommandHandler;

public class ReadArticleHandler implements CommandHandler {
	private ReadArticleService readService = new ReadArticleService();
	private ListCommentService listService = new ListCommentService();
	
	@Override
	public String process(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession();
		
		// 게시글번호 받아옴
		String noVal = req.getParameter("no");
		session.setAttribute("listNo", noVal);
		int articleNum = Integer.parseInt(noVal);
		
		try {
			
			ArticleData articleData = readService.getArticle(articleNum, true);
			session.setAttribute("articleData", articleData);
			
			NumForComment numForComment = new NumForComment(articleData.getArticle().getNumber());
			session.setAttribute("NumForComment", numForComment.getArticleNumber());
			System.out.println(numForComment.getArticleNumber()+"게시글번호");
			
			// 댓글 리스트 페이지 번호 리퀘스트에서 받아옴
			String pageNoVal = req.getParameter("pageCno");
			int pageNo = 1;
			
			if(pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}
			
			CommentPage commentPage = listService.getCommentPage(numForComment.getArticleNumber(), pageNo);
			
			// 댓글 정보 세션에 저장
			session.setAttribute("commentPage", commentPage);
			
			return "/WEB-INF/view/readArticle.jsp";
			
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
			return null;
		}
	}
}









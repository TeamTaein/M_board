package article.command;

import java.util.Date;

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
		String noVal = req.getParameter("no");
		
		int articleNum = Integer.parseInt(noVal);
		
		
		try {
				
			ArticleData articleData = readService.getArticle(articleNum, true);
				System.out.println(1);
			NumForComment numForComment = new NumForComment(articleData.getArticle().getNumber());
				System.out.println(2);
			session.setAttribute("NumForComment", numForComment.getArticleNumber());
				System.out.println(numForComment.getArticleNumber());
			//게시글 정보 세션 저장
			session.setAttribute("articleData", articleData);
				System.out.println(3);
			String pageNoVal = req.getParameter("pageCno");
				System.out.println(4);
			int pageNo =1;
				System.out.println(5);
			if(pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}	System.out.println(6);
			
				CommentPage commentPage = listService.getCommentPage(numForComment.getArticleNumber(),pageNo);
					System.out.println(7);
				String commentWriter = listService.getCommentWriter(numForComment.getArticleNumber());
					System.out.println(8);
				Date modate = listService.regdateContent(numForComment.getArticleNumber()).getRegDate();
					System.out.println(9);
				String commentContent = listService.regdateContent(numForComment.getArticleNumber()).getCommentContent();
				//댓글정보 세션저장
				session.setAttribute("commentPage", commentPage);
				session.setAttribute("commentWriter", commentWriter);
				session.setAttribute("commentModate", modate);
				session.setAttribute("recievedCommentContent", commentContent);
				
				System.out.println("되나?");
			return "/WEB-INF/view/readArticle.jsp";
			
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
			return null;
		}
	}
	
}










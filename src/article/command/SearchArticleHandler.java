package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.SearchArticleService;
import auth.service.ArticlePage;
import mvc.controller.CommandHandler; 

public class SearchArticleHandler implements CommandHandler {
	 
	private SearchArticleService searchArticleService = new SearchArticleService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//pageNo 파라미터 값을 이용해서 읽어올 페이지 번호를 구한다
		String pageNoVal = req.getParameter("pageNo");
		String localName = req.getParameter("localName");
		int pageNo = 1;
		
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		//ListArticleHandler를 이용해서 지정한 페이지 번호에 해당하는 게시글 데이터를 구한다
		ArticlePage articlePage = searchArticleService.getArticlePage(pageNo, localName);
		//ArticlePage 객체를 jsp에서 사용할 수 있도록 request의 articlePage속성에 지정
		req.setAttribute("articlePage", articlePage);
		
		return "/WEB-INF/view/listArticle.jsp";	
	}
}

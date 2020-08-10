package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.service.SearchArticleService;
import auth.service.ArticlePage;
import mvc.controller.CommandHandler;

public class SearchArticleHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/searchArticle.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		
		String searchKey = req.getParameter("search_key");
		String searchRs = req.getParameter("search_rs");
 	
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		
		System.out.println(1);
		if(searchRs != null) {
			searchRs = req.getParameter("search_rs");
			session.setAttribute("search_rs", searchRs);
			}
		
		if(searchKey != null) {
			searchKey = req.getParameter("search_key");		
			session.setAttribute("search_key", searchKey);
		}
		
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		String searchKeyValue = (String)session.getAttribute("search_key");
		String searchRsValue = (String)session.getAttribute("search_rs");
		
		 ArticlePage articlePage = SearchArticleService.getArticlePage(pageNo, searchKeyValue, searchRsValue);
		//ArticlePage 객체를 jsp에서 사용할 수 있도록 request의 articlePage속성에 지정
		req.setAttribute("articlePage", articlePage);
		
		return FORM_VIEW;
	}
	
}

package article.command;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import article.service.SearchArticleService;
import auth.service.ArticlePage;
import auth.service.ListArticleService;
import mvc.controller.CommandHandler;

public class ListArticleHandler implements CommandHandler {
	 
	private ListArticleService listService = new ListArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) { 
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
			return null;
		}
		}
		

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		//search_key->지역 제목 작성자 중 선택값
				//search_rs-> 검색창 폼 입력값
				
				String searchKey = req.getParameter("search_key");
				String searchRs = req.getParameter("search_rs");
				String searchRsValue = null;
				String searchKeyValue = null;
				
				if(searchRs != null) {
					searchRsValue = searchRs;
				}
				if(searchKey != null) {
					searchKeyValue = searchKey;
				}
				
				//ListArticleHandler를 이용해서 Rsvalue 에 해당하는 Rskey값을 적용해 해당하는 게시글을 불러온다
				
				ArticlePage articlePage = SearchArticleService.getArticlePage(searchKeyValue,searchRsValue);
				//ArticlePage 객체를 jsp에서 사용할 수 있도록 request의 articlePage속성에 지정
				req.setAttribute("articlePage", articlePage);
				
				return "/WEB-INF/view/listArticle.jsp";	
			}
		
	

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		//pageNo 파라미터 값을 이용해서 읽어올 페이지 번호를 구한다
				String pageNoVal = req.getParameter("pageNo");
				int pageNo = 1;
				
				if(pageNoVal != null) {
					pageNo = Integer.parseInt(pageNoVal);
				}
				
				//ListArticleHandler를 이용해서 지정한 페이지 번호에 해당하는 게시글 데이터를 구한다
				ArticlePage articlePage = listService.getArticlePage(pageNo);
				//ArticlePage 객체를 jsp에서 사용할 수 있도록 request의 articlePage속성에 지정
				req.setAttribute("articlePage", articlePage);
				
				return "/WEB-INF/view/listArticle.jsp";	
			}

	}
	
	
	


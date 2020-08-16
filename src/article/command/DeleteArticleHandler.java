package article.command;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import article.service.DeleteArticleRequest;
import article.service.DeleteArticleService;
import mvc.controller.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {
	private DeleteArticleService deleteService = new DeleteArticleService();
	private static final String FORM_VIEW ="/WEB-INF/view/readArticle.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String numTemp = req.getParameter("no");
		int no = Integer.parseInt(numTemp);
		DeleteArticleRequest deleteReq = new DeleteArticleRequest(no);
		req.setAttribute("deleteReq", deleteReq);


		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		deleteReq.validate(errors);


		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}try {
			deleteService.delete(deleteReq);
			return "list.do";
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}



}


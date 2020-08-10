package comment.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import comment.model.CommentWriter;
import comment.service.WriteCommentRequest;
import comment.service.WriteCommentService;
import mvc.controller.CommandHandler;

public class WriteCommentHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/readArticle.jsp";
	private WriteCommentService commentWriteService = new WriteCommentService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Map<String, Boolean>errors = new HashMap<>();
		req.setAttribute("errors", errors);
		Integer ArticleNum = (Integer)session.getAttribute("NumforComment");
		// 댓글에 해당되는 페이지 이름 세션값으로 받아오기
		User user =(User)session.getAttribute("authUser");
		// 로그인 세션으로 댓글 작성자 정보 세션값으로 받아오기 
		WriteCommentRequest writeReq = createCommentWriteRequest(ArticleNum,user,req);
		System.out.println(writeReq.getCommentWriter()+ "핸들러에서 작성자 값 받음");
		System.out.println(writeReq.getArticleNum()+"핸들러에서 게시글 번호 값 받음");
		writeReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		Integer newCommentNo = commentWriteService.write(writeReq);
		req.setAttribute("newCommentNo", newCommentNo);
		System.out.println(newCommentNo+"서비스 돌아가 핸들러에서 댓글 번호 받음");
		
		return FORM_VIEW;
	}

	private WriteCommentRequest createCommentWriteRequest(Integer ArticleNum, User user, HttpServletRequest req) {
		return new WriteCommentRequest(ArticleNum, new CommentWriter(user.getName()),req.getParameter("commentContent"));
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return FORM_VIEW;
	}

}

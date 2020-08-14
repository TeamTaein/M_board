package comment.command;

import java.io.IOException;
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

public class WriteCommentHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/readArticle.jsp";
	private WriteCommentService writeCommentService = new WriteCommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}		
	}

	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		//댓글에 해당되는 페이지 이름 세션값으로 받아오기
		Integer ArticleNum =(Integer)session.getAttribute("NumForComment");
		System.out.println(ArticleNum+"게시글번호2");
		//세션에서 로그인한 사용자 정보를 구한다
		User user = (User) session.getAttribute("authUser");
		
		// user와 HttpServletRequest를 이용해서 WriteCommentRequest 객체를 생성
		WriteCommentRequest commentReq = createCommentRequest(ArticleNum, user, req);
		System.out.println("작성자:  "+commentReq.getCommentWriter());
		System.out.println("게시글번호:  "+commentReq.getArticleNum());
		// commentReq 객체가 유효한지 검사
		commentReq.validate(errors);
		
		//에러가 존재하면 폼을 다시 보여줌
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
			
		
		//WriteCommentService를 이용해서 댓글을 등록, 등록된 게시글의  댓글번호 리턴
		Integer newCommentNo = writeCommentService.write(commentReq);
		
		// 새 댓글의 번호를 request의 newCommentNo 속성에 저장, 처리결과를 보여줄 jsp는 이 속성값을 사용해서 링크 생성
		req.setAttribute("newCommentNo", newCommentNo);
		System.out.println("서비스가 실행되어 핸들러에서 댓글번호 받음:  "+newCommentNo);
		
		String listPageNo = session.getAttribute("listNo").toString();
		try {
			res.sendRedirect("read.do?no=" + ArticleNum + "&pageNo=" + listPageNo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return FORM_VIEW;
	}

	private WriteCommentRequest createCommentRequest(Integer ArticleNum,User user, HttpServletRequest req) {
		
		return new WriteCommentRequest(ArticleNum ,new CommentWriter(user.getName()), req.getParameter("comment_content"));
	}


	
	
}

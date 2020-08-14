package test;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.dao.CommentDao;
import comment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

/**
 * Servlet implementation class MessageDaoInsertTestServlet
 */
@WebServlet("/MessageDaoInsertTestServlet")
public class MessageDaoInsertTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageDaoInsertTestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;

		/* Comment comment = new Comment(1, 1, "test", new Date(), "testcontent"); */
		try {
			conn = ConnectionProvider.getConnection();
			
			/*
			 * CommentDao dao = new CommentDao(); Comment cm = dao.insert(conn, comment);
			 * 
			 * 
			 * System.out.println(cm.getCmtNum()); System.out.println(cm.getNumber());
			 * System.out.println(cm.getCommentWriter().getName());
			 * System.out.println(cm.getCmtRegdate());
			 * System.out.println(cm.getCmtContent());
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

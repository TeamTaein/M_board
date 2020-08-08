package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sun.javafx.scene.layout.region.Margins.Converter;

import article.dao.ArticleDao;
import article.model.Article;
import auth.service.ArticlePage;
import jdbc.connection.ConnectionProvider;

public class SearchArticleService {
	
	private static ArticleDao articleDao = new ArticleDao();
	private static int size = 10;
	
	public static ArticlePage getArticlePage(String searchKey, String searchRs) {
		try(Connection conn = ConnectionProvider.getConnection()){
			

			//전체 게시글의 개수를 구한다
			int total = articleDao.selectSearchCount(conn, searchKey, searchRs);
			
			//System.out.println("----------------------------------total	:	 " + total );
			
			//pageNum에 해당하는 게시글 목록을 구한다
			//articleDao.select의 두번째 파라미터는 조회할 레코드의 시작 행
			List<Article> content = articleDao.selectSearch(conn, searchKey, searchRs, 0, size);
			
			//System.out.println("----------------------------------content	:	 " + content.size());
			
			//ArticlePage 객체 리턴
			return new ArticlePage(total, 1, size, content);
	 		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.toString());
			throw new RuntimeException(e);
		}
	}
	
}

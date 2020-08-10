package comment.service;
import java.util.List;
import comment.model.Comment;

public class CommentPage {
	private Integer articleNum;
	private int total; // 게시물 안 전체 댓글 개수
	private int currentPage; // 현재 댓글 페이지
	private  List<Comment> content; //한 게시물 안 화면에 출력할 게시물 배열로 저장
	private int totalPages; // 게시물 안 전체 댓글의 개수
	private int startPage;	// 시작 댓글페이지의 번호
	private int endPage;	//끝번호
	
	
	public CommentPage(Integer articleNum, int total, int currentPage, int size, List<Comment> content) {
		this.articleNum = articleNum;
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if(totalPages ==0) {
			totalPages=0;
			startPage = 0;
			endPage =0;
			
		}else {
			startPage = (currentPage-1)/5*5+1;
			endPage = startPage+4;
			endPage = Math.min(endPage, totalPages);
		}
		
	}
	public Integer articleNum() {
		return articleNum;
	}
	
	public int getTotal() {
		return total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Comment> getContent() {
		return content;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getstartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	
}
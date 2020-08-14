package comment.service;

import java.util.List;

import comment.model.Comment;

public class CommentPage {
	private Integer articleNum;	//게시글 번호
	private int total;	//전체 댓글 수
	private int currentPage;	//현재 댓글 페이지
	private List<Comment> content;	//하나의 게시물 화면에 출력할 댓글 배열로 저장
	private int totalPages;	// 하나의 게시글 전체 댓글의 개수
	private int startPage;	// 시작 댓글 페이지 번호
	private int endPage;	//끝 번호
	
	public CommentPage(Integer articleNum, int total, int currentPage, int size, List<Comment>content) {
		this.articleNum = articleNum;
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		
		if(total == 0) {
			totalPages=0;
			startPage=0;
			endPage=0;
		} else {
			totalPages = total/size;
			if(total%size>0) {
				totalPages++;				
			}
			startPage=((currentPage-1)/5 * 5) + 1;
			endPage = startPage + 4;
			endPage = Math.min(endPage, totalPages);
		}
	}

	public Integer getArticleNum() {
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

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
	public boolean hasNoComments() {
		return total == 0;
	}
	
	public boolean hasComments() {
		return total>0;
	}

}

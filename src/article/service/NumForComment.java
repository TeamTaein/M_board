package article.service;

//게시글번호 저장
public class NumForComment {
	
	private Integer articleNumber;
	
	public NumForComment(Integer articleNumber) {
		this.articleNumber = articleNumber;
	}

	public Integer getArticleNumber() {
		return articleNumber;
	}
}

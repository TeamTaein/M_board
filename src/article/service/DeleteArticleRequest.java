package article.service;
import java.util.Map;
public class DeleteArticleRequest {
	private int articleNumber;

	

	public int getArticleNumber() {
		return articleNumber;
	}
	public DeleteArticleRequest(int articleNumber) {
		super();
		this.articleNumber = articleNumber;
	}
	public void validate(Map<String ,Boolean> errors) {
		if(articleNumber ==0) {
			errors.put("no", Boolean.TRUE);
		}
	}

}


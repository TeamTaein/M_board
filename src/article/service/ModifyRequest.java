package article.service;

import java.util.Map;

public class ModifyRequest {
	
	private String userName;    // id -> email->Name 수정
	private int articleNumber;
	private String title;
	private String content;
	private String fileName;
	private String localName;// filename 추가
	
	public ModifyRequest(String userName, int articleNumber,
			String title, String content, String fileName, String localName) {
		super();
		this.userName = userName;
		this.articleNumber = articleNumber;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
		this.localName = localName;
	}
	public String getUserName() {
		return userName;
	}
	public int getArticleNumber() {
		return articleNumber;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getFileName() {
		return fileName;
	}
	public String getLocalName() {
		return localName;
	}
	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", true);
		}
	}

}

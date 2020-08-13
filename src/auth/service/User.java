package auth.service;

public class User {
	private String email;
	private String id;
	
	public User(String email, String id) {
		this.email = email;
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public String getId() {
		return id;
	}
 
}

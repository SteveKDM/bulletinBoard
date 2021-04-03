package model;

// ログイン情報を表すEntity
public class LoginUser {
	private String userId;
	private String pass;

	public LoginUser(String userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}

	public String getUserId() { return this.userId; }
	public String getPass() { return this.pass; }
}

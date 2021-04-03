package model;

import java.io.Serializable;

// ACCOUNTテーブルのレコードを表すJavaBeans
public class Account implements Serializable {
	private String userId;
	private String pass;
	private String name;

	public Account() {}

	public Account(String userId, String pass, String name) {
		this.userId = userId;
		this.pass = pass;
		this.name = name;
	}

	public String getUserId() { return this.userId; }
	public String getPass() { return this.pass; }
	public String getName() { return this.name; }
}

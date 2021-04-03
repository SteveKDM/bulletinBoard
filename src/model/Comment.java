package model;

import java.io.Serializable;

// 投稿情報を持つJavaBeans
public class Comment implements Serializable {
	private int id;
	private String userId;
	private String text;
	private String name;
	private String dateTime;

	public Comment() {}
	public Comment(String userId, String text, String name) {
		this.userId = userId;
		this.text = text;
		this.name = name;
	}
	public Comment(int id, String userId, String text, String name, String dateTime) {
		this.id = id;
		this.userId = userId;
		this.text = text;
		this.name = name;
		this.dateTime = dateTime;
	}

	public int getId() { return this.id; }
	public String getUserId() { return this.userId; }
	public String getText() { return this.text; }
	public String getName() { return this.name; }
	public String getDateTime() { return this.dateTime; }
}

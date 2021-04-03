package model;

import dao.CommentDAO;

// 投稿の処理を行うモデル（DAOを利用）
public class PostCommentLogic {
	public void excute(Comment comment) {
		CommentDAO dao = new CommentDAO();
		dao.create(comment);
	}
}

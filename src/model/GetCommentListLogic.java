package model;

import java.util.List;

import dao.CommentDAO;

// 投稿の取得に関する処理を行うモデル（DAOを利用）
public class GetCommentListLogic {
	public List<Comment> execute() {
		CommentDAO dao = new CommentDAO();
		List<Comment> commentList = dao.findAll();
		return commentList;
	}
}

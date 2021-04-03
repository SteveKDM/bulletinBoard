package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comment;

// COMMENTテーブルを担当するDAO
public class CommentDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost/bulletinBoard";
	private final String DB_USER = "root";
	private final String DB_PASS = "rootPass";

	// 全てのレコードを取得するメソッド
	public List<Comment> findAll() {
		List<Comment> commentList = new ArrayList<>();

		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文の準備
			String sql = "SELECT COMMENT_ID, COMMENT.USER_ID, NAME, TEXT, DATETIME FROM COMMENT JOIN ACCOUNT ON COMMENT.USER_ID = ACCOUNT.USER_ID ORDER BY COMMENT_ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("COMMENT_ID");
				String userId = rs.getString("USER_ID");
				String text = rs.getString("TEXT");
				String name = rs.getString("NAME");
				String dateTime = rs.getString("DATETIME");
				Comment comment = new Comment(id, userId, text, name, dateTime);
				commentList.add(comment);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return commentList;
	}

	// コメントを投稿するメソッド
	public boolean create(Comment comment) {
		// データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// INSERT文の準備（idは自動連番）
			String sql = "INSERT INTO COMMENT (USER_ID, TEXT, DATETIME) VALUES (?, ?, NOW())";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, comment.getUserId());
			pStmt.setString(2, comment.getText());

			// INSERT文を実行（resultには追加された行数が代入される）
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

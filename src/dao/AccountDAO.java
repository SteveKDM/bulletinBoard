package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.LoginUser;

public class AccountDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost/bulletinBoard";
	private final String DB_USER = "root";
	private final String DB_PASS = "rootPass";

	// ログインユーザーの検索
	public Account findByLogin(LoginUser loginUser) {
		Account account = null;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文を準備
			String sql = "SELECT USER_ID, PASS, NAME FROM ACCOUNT WHERE ? = USER_ID AND ? = PASS";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginUser.getUserId());
			pStmt.setString(2, loginUser.getPass());

			// SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 一致したユーザーが存在した場合、そのユーザーを表すAccountインスタンスを生成
			if (rs.next()) {
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String name = rs.getString("NAME");
				account = new Account(userId, pass, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// 見つかったユーザーまたはnullを返す
		return account;
	}

	// ユーザー登録
	public boolean createAccount(Account account) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// INSERT文を準備
			String sql = "INSERT INTO ACCOUNT(USER_ID, PASS, NAME) VALUES(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getUserId());
			pStmt.setString(2, account.getPass());
			pStmt.setString(3, account.getName());

			// INSERTを実行
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

	// 登録予定ユーザーIDが既存ユーザーIDにあるか確認
	public boolean checkUserIdName(Account account) {
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文を準備
			String sql = "SELECT USER_ID, NAME FROM ACCOUNT WHERE USER_ID = ? OR NAME = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getUserId());
			pStmt.setString(2, account.getName());

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 一致したユーザーが存在した場合、falseを返す
			if(rs.next()) {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

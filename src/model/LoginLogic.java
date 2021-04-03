package model;

import dao.AccountDAO;

// ログイン処理を行うBO
public class LoginLogic {
	public Account excute(LoginUser loginUser) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.findByLogin(loginUser);
		return account; // 取得していなければnullを返す
	}
}

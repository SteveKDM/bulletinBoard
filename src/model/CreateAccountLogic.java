package model;

import dao.AccountDAO;

// ユーザー登録処理を行うモデル（DAOを利用）

public class CreateAccountLogic {
	public void execute(Account account) {
		AccountDAO dao = new AccountDAO();
		dao.createAccount(account);
	}
}

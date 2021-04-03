package model;

import dao.AccountDAO;

// 登録予定ユーザーが既存ユーザーと同じか確認するモデル
public class CheckAccountLogic {
	public boolean execute(Account account) {
		AccountDAO dao = new AccountDAO();
		boolean result = dao.checkUserIdName(account);
		return result;
	}
}

package test;

import model.Account;
import model.LoginLogic;
import model.LoginUser;

public class LoginLogicTest {
	public static void main(String[] args) {
		testExecute1(); // ログイン成功のテスト
		testExecute2(); // ログイン失敗のテスト
	}

	public static void testExecute1() {
		LoginUser login = new LoginUser("firstUser", "12345");
		LoginLogic bo = new LoginLogic();
		Account account = bo.excute(login);
		if (account != null) {
			System.out.println("testExecute1:成功しました");
		} else {
			System.out.println("testExecute1:失敗しました");
		}
	}

	public static void testExecute2() {
		LoginUser login = new LoginUser("firstUser", "98765");
		LoginLogic bo = new LoginLogic();
		Account account = bo.excute(login);
		if (account == null) {
			System.out.println("testExecute2:成功しました");
		} else {
			System.out.println("testExecute2:失敗しました");
		}
	}
}

package test;

import dao.AccountDAO;
import model.Account;
import model.LoginUser;

public class AccountDAOTest {
	public static void main(String[] args) {
		testFindByLogin1(); // ユーザーが見つかる場合のテスト
		testFindByLogin2(); // ユーザーが見つからない場合のテスト
	}

	public static void testFindByLogin1() {
		LoginUser login = new LoginUser("firstUser", "12345");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		if(result != null &&
			result.getUserId().equals("firstUser") &&
			result.getPass().equals("12345") &&
			result.getName().equals("John")) {
			System.out.println("testByLogin1:成功しました");
		} else {
			System.out.println("testByLogin1:失敗しました");
		}
	}

	public static void testFindByLogin2() {
		LoginUser login = new LoginUser("firstUser", "98765");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		if(result == null) {
			System.out.println("testByLogin2:成功しました");
		} else {
			System.out.println("testByLogin2:失敗しました");
		}
	}
}

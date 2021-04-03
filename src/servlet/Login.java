package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.LoginLogic;
import model.LoginUser;

// ログインのリクエストを処理するコントローラー
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		// request.setCharacterEncoding("UTF-8"); リスナーで処理
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");

		// LoginUserインスタンス（ユーザー情報）の生成
		LoginUser loginUser = new LoginUser(userId, pass);

		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		Account account = loginLogic.excute(loginUser);

		// ログイン成功時の処理
		if(account != null) {
		// ユーザー情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
	    }

		// ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}

}

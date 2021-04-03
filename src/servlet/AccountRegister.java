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
import model.CheckAccountLogic;
import model.CreateAccountLogic;

// アカウント登録処理をするコントローラー
/**
 * Servlet implementation class AccountRegister
 */
@WebServlet("/AccountRegister")
public class AccountRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		// request.setCharacterEncoding("UTF-8"); filterで処理
		String action = request.getParameter("action");

		if(action == null) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountRegister.jsp");
			dispatcher.forward(request, response);
		} else if(action.equals("register")) { // ユーザー登録処理
			// セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");

			// ユーザー登録
			CreateAccountLogic createAccountLogic = new CreateAccountLogic();
			createAccountLogic.execute(account);
		}

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得"
		// request.setCharacterEncoding("UTF-8"); リスナーで処理
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");

		Account account = new Account(userId, pass, name);

		CheckAccountLogic checkAccountLogic = new CheckAccountLogic();
		boolean result = checkAccountLogic.execute(account);

		if(userId.length() == 0 || pass.length() == 0 || name.length() == 0) {
			// 未入力項目があった場合のエラーメッセージをリクエストスコープに保存
			String errorMsg = "未入力項目がありました";
			request.setAttribute("errorMsg", errorMsg);

			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountRegister.jsp");
			dispatcher.forward(request, response);
		} else if(!result) {
			// ユーザーID、名前が既に使われている場合のエラーメッセージをリスクエストスコープに保存
			String errorMsg = "登録できませんでした。「ユーザーID」「名前」が既に使われている可能性があります。";
			request.setAttribute("errorMsg", errorMsg);

			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountRegister.jsp");
			dispatcher.forward(request, response);
		} else {
			// セッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("account", account);

			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountRegisterConfirm.jsp");
			dispatcher.forward(request, response);
		}
	}

}

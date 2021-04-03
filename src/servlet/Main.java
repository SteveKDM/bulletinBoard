package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Comment;
import model.GetCommentListLogic;
import model.PostCommentLogic;
// 投稿に関するリクエストを処理するコントローラー
/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 投稿リストを取得して、リクエストスコープに保存
		GetCommentListLogic getCommentListLogic = new GetCommentListLogic();
		List<Comment> commentList = getCommentListLogic.execute();
		request.setAttribute("commentList", commentList);

		// ログインしているか確認するためセッションスコープからアカウント情報を取得
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");

		// ログインしていない場合
		if(account == null) {
			// リダイレクト
			response.sendRedirect("/bullutenBoard/");
		} else { // ログイン済みの場合
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		// request.setCharacterEncoding("UTF-8"); リスナーで処理
		String text = request.getParameter("text");

		// 入力値チェック
		if(text != null && text.length() != 0) {
			// セッションスコープに保存されたアカウント情報を取得
			HttpSession session = request.getSession();
			Account account= (Account) session.getAttribute("account");

			// 投稿を投稿リストに追加
			Comment comment = new Comment(account.getUserId(), text, account.getName());
			PostCommentLogic postCommentLogic = new PostCommentLogic();
			postCommentLogic.excute(comment);
		} else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "何も入力されていません");
		}
		// 投稿リストを取得して、リクエストスコープに保存
		List<Comment> commentList = new ArrayList<>();
		GetCommentListLogic getCommentListLogic = new GetCommentListLogic();
		commentList = getCommentListLogic.execute();
		request.setAttribute("commentList", commentList);

		// メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}

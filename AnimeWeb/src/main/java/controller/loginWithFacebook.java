package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import Log.Log;
import database.DAOAccounts;
import database.JDBiConnector;
import model.Account;

/**
 * Servlet implementation class loginWithFacebook
 */
@WebServlet("/anime-main/loginWithFacebook")
public class loginWithFacebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");
		String name = null, email = null, id = null, picture = null;
		String ipClient = request.getRemoteAddr();
		Log log = new Log(0, -1, ipClient, "loginWithFacebook", null, 0);
		if (action.equals("Face")) {
			String lastName = request.getParameter("last_name");
			String firstName = request.getParameter("first_name");
			name = lastName + firstName;
			email = request.getParameter("email");
			picture = request.getParameter("picture");
			String height = request.getParameter("height");
			String width = request.getParameter("width");
			String hash = request.getParameter("hash");
			String ext = request.getParameter("ext");
			picture=picture+"&height="+height+"&width="+width+"&ext="+ext+"&hash="+hash;
			id = request.getParameter("id");
			System.out.println(name);
			System.out.println(email);
			System.out.println(id);
			System.out.println(picture);

		}
		Account account = null;

		DAOAccounts dao = new DAOAccounts();
		try {
			int idUser = dao.findIdUserAccount(id, 3);
			System.out.println(idUser);

			if (idUser != 0) {
				account=dao.loginAccountSocialId(idUser,3);
				session.setAttribute("user", account);
//				session.setAttribute("isAdmin", account.isAdmin());
				log.setUserId(account.getId());
				log.setContent("Log in FB succes");
				log.setLevel(log.INFO);
				new JDBiConnector().insert(log);
				request.getRequestDispatcher("/anime-main/index.jsp").forward(request, response);

			} else if (idUser == 0 && (!name.equalsIgnoreCase("undefined"))
					&& (!email.equalsIgnoreCase("undefined"))) {
				dao.createAccountByFB(id,email,name,picture,name);
				int idAccount=dao.findIdUserAccount(id,3);
				System.out.println(idAccount);

				account = dao.loginAccountSocialId(idAccount,3);

				session.setAttribute("user", account);
//				session.setAttribute("isAdmin", account.isAdmin());
				log.setContent("create account by FB and Log in succes");
				log.setLevel(log.ALERT);
				new JDBiConnector().insert(log);
				request.getRequestDispatcher("/anime-main/index.jsp").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}

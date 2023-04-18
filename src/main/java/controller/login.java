package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Log.Log;
import database.DAOAccounts;

import database.JDBiConnector;
import model.Account;
import model.Encode;

@WebServlet("/anime-main/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Encode encrypt = new Encode();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String userName = request.getParameter("loginName");
		String passWord = request.getParameter("loginPassword");
		HttpSession session = request.getSession();
		String error = String.valueOf(session.getAttribute("countError"));


		Account user = null;
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		//boolean verify = model.VerifyRecaptcha.verify(gRecaptchaResponse);
		DAOAccounts daoAccount = new DAOAccounts();
		String ipClient =request.getRemoteAddr();
	
		Log log = new Log(Log.INFO, -1, ipClient, "LoginServlet", null, 0);
	
		String direct = "/anime-main/login.jsp";
		try {
		
					//if (verify) {
						user = daoAccount.Login(userName, passWord);
						
						if (user != null) {
						log.setUserId(user.getId());
						if (user.getIsActive() == 1) {
							session.setAttribute("user", user);

							session.removeAttribute("countError");
							direct="/anime-main/Index";
							session.removeAttribute("errorLogin");
							log.setContent("Login sucess");
						
						} else {
							session.setAttribute("errorLogin",
									"Tài khoản của bạn đã bị khóa do nhập sai quá nhiều lần, vui lòng liên hệ quản trị viên để mở khóa");
							
							log.setContent("Account has been locked");
							
						}
					} else {
						int idUser = daoAccount.findIdByUserName(userName);
						int countError = 1;
						if (idUser != -1) {
							log.setUserId(idUser);
							String oldUserName = (String) session.getAttribute("oldUserName");
							session.setAttribute("errorLogin", "Sai mật khảu");
							if (!error.equals("null")) {
								if (userName.equalsIgnoreCase(oldUserName)) {
									countError = Integer.parseInt(error) + 1;
								} else {
									session.setAttribute("oldUserName", userName);
								}

							}
							
							log.setContent("login fail");
							log.setLevel(Log.ALERT);
							session.setAttribute("countError", countError);

							if (countError >= 5) {
								
								daoAccount.blockAccount(idUser);
								log.setLevel(Log.DANGER);
								log.setContent("Lock account");
								
								session.setAttribute("errorLogin",
										"Tài khoản của bạn đã bị khóa do nhập sai quá nhiều lần, vui lòng liên hệ quản trị viên để mở khóa");
								
							}
							
						} else {
							log.setContent("login fail");
							log.setLevel(Log.ALERT);
							session.setAttribute("errorLogin", "Tài khoản không tồn tại");
							
						}
					}
				//} else {
				//	log.setContent("error captcha");
				//	log.setLevel(Log.ALERT);
				//	session.setAttribute("errorLogin", "Lỗi captcha");
					
				//}
					new JDBiConnector().insert(log);
				response.sendRedirect(getServletContext().getContextPath() +direct);
			
		} catch (SQLException e) {
		e.printStackTrace();
			response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png" + "\">");
		}



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

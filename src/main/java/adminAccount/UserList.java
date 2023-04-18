package adminAccount;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Log.Log;
import database.DAOAccounts;
import database.JDBiConnector;
import model.Account;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/admin/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public UserList() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String offsetAttribue = (String) request.getParameter("offset");

		int sizeAccountList = DAOAccounts.getSizeListAccountNormal();
		int countNormalAccount =sizeAccountList>=7?sizeAccountList/7:sizeAccountList;
		int limitList = countNormalAccount%7==0?countNormalAccount:countNormalAccount+1;
		Account account = (Account) request.getSession().getAttribute("user");
		String ipClient =request.getRemoteAddr();

		Log log = new Log(Log.INFO, account.getId(), ipClient, "UserList Servlet", "Lấy danh sách tài khoản thường", 0);

		int offset = offsetAttribue==null?1:Integer.parseInt(offsetAttribue);
		if(limitList<1) limitList=1;
		if(offset<1) offset=1;
		if(offset>limitList) offset =limitList;

		List<Account> listAccount = DAOAccounts.getListAccount((offset-1)*7, 7);

		request.setAttribute("offset", offset);
		request.setAttribute("listAccount", listAccount);
		request.setAttribute("limitList", limitList);
		new JDBiConnector().insert(log);
		request.getRequestDispatcher("/admin/User-List.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

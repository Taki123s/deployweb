package adminAccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Log.Log;
import database.DAOAccounts;
import database.DAORoleAccount;
import database.JDBiConnector;
import model.Account;
import security.PermissionValidate;

/**
 * Servlet implementation class UserEdit
 */
@WebServlet("/admin/UserEdit")
public class UserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserEdit() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Account user = (Account) request.getSession().getAttribute("user");
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		Account account = DAOAccounts.findAccountById(idUser);
		String ipClient = request.getRemoteAddr();
		Log log = new Log(Log.INFO, user.getId(), ipClient, "User edit", null, 0);
		JDBiConnector jdBiConnector = new JDBiConnector();
//			if(!PermissionValidate.userHasPermission(account.getId(),"/admin/UserEdit","edit","admin",false,0,0)){
//				response.sendError(403, "Bạn không có quyền truy cập");
//				log.setLevel(Log.DANGER);
//				log.setContent("Truy cập chỉnh sửa thông tin user có id = "+idUser +"Không thành công do không đủ quyền");
//				jdBiConnector.insert(log);
//				return;
//			}




		request.setAttribute("account", account);
		request.setAttribute("listUnableRole", DAORoleAccount.getUnableAddRoles(account,user));

		log.setContent("Truy cập chỉnh sửa thông tin user có id = "+idUser);
		jdBiConnector.insert(log);
		request.getRequestDispatcher("/admin/User-Edit.jsp").forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

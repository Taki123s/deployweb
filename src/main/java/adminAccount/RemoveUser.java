package adminAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Log.Log;
import database.DAOAccounts;
import database.DAOPermission;
import database.DAORoleAccount;
import database.JDBiConnector;
import model.Account;
import security.PermissionValidate;
import socket.UserUpdateEndpoint;

/**
 * Servlet implementation class RemoveUser
 */
@WebServlet("/admin/RemoveUser")
public class RemoveUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public RemoveUser() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ipClient = request.getRemoteAddr();
		Account user = (Account) request.getSession().getAttribute("user");
		boolean checkPermission = PermissionValidate.userHasPermission(user,"user","delete","admin",false,0,0);
//		if(!checkPermission){
//			response.sendError(403, "Bạn không có quyền truy cập");
//			return;
//		}
		Log log = new Log(Log.WARNING, user.getId(), ipClient, "RemoveUser", null, 0);
		JDBiConnector jdBiConnector = new JDBiConnector();
		
		
		int idUser = Integer.parseInt(request.getParameter("idUser"));

		boolean check = DAOAccounts.removeUserbyId(idUser);
		log.setContent("xóa user có id = "+idUser +"thất bại");
		if(check) {
			log.setContent("xóa user có id = "+idUser +"thành công");

		}
		jdBiConnector.insert(log);
		UserUpdateEndpoint.notifyUserUpdate(idUser,"removeUser");
		response.sendRedirect(getServletContext().getContextPath()+"/admin/UserList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

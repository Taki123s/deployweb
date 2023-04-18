package adminAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Log.Log;
import database.DAOAccounts;
import database.JDBiConnector;
import model.Account;
import security.PermissionValidate;


@WebServlet("/admin/UnlockUser")
public class UnlockUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UnlockUser() {
		super();
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Account user = (Account) request.getSession().getAttribute("user");

		boolean checkPermission = PermissionValidate.userHasPermission(user,"user","edit","admin",false,0,0);
		if(!checkPermission){
			response.sendError(403, "Bạn không có quyền truy cập");
			return;
		}
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		response.setContentType("text/html;charset=UTF-8");
		DAOAccounts daoAccount = new DAOAccounts();
		boolean check = daoAccount.unlockUser(idUser);
		String ipClient = request.getRemoteAddr();

		Log log = new Log(Log.ALERT, user.getId(), ipClient, "UnlockUser", null, 0);
		JDBiConnector jdBiConnector = new JDBiConnector();
		Gson gson = new Gson();
		JsonArray array1 = new JsonArray();
		array1.add(gson.toJson(check));
		JsonArray array2 = new JsonArray();
		JsonObject object = new JsonObject();
		log.setContent("Mở khóa tài khoản cho user id ="+idUser+"thất bại");
		if (check) {
			log.setContent("Mở khóa tài khoản cho user id ="+idUser+"thành công");
			array2.add(gson.toJson(Account.getIsActiveDescription(Account.ACTIVED)));

		}
		jdBiConnector.insert(log);
		object.add("isSuccess", array1);
		object.add("newData", array2);
		response.getWriter().print(gson.toJson(object));
		response.getWriter().flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}

}

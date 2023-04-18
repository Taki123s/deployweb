package adminAccount;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import database.DAOPermission;
import database.DAORoleAccount;
import database.JDBiConnector;
import model.Account;
import model.Role;
import socket.UserUpdateEndpoint;

@WebServlet("/admin/RemoveRoleUser")
public class RemoveRoleUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveRoleUser() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



			int idUser = Integer.parseInt(request.getParameter("idUser"));
			int idRole = Integer.parseInt(request.getParameter("idRole"));

			String ipClient = request.getRemoteAddr();
			Account user = (Account) request.getSession().getAttribute("user");
			Log log = new Log(Log.ALERT, user.getId(), ipClient, "RemoveRoleUser", null, 0);
			JDBiConnector jdBiConnector = new JDBiConnector();


			boolean check = DAORoleAccount.removeRoleUser(idUser, idRole);
			log.setContent("Xóa quyền có id =" + idRole + "cho User có id " + idUser + "thất bại");

			if (check) {
				log.setLevel(Log.WARNING);
				log.setContent("Xóa quyền có id =" + idRole + "cho User có id " + idUser + "thành công");

			}
			Gson gson = new Gson();
			Account account = DAOAccounts.findAccountById(idUser);
			Map<String,Role> updateRoleUser = DAOAccounts.getRoleUser(idUser);
			List<Role> newEnableListRole = DAORoleAccount.getUnableAddRoles(account,user);
			JsonArray jsonArray1 = new JsonArray();
			for(Role r : updateRoleUser.values()){
				r.setEnablePermission(DAOPermission.getEnablePermission(idUser,r.getId()));
			}
			jsonArray1.add(gson.toJson(updateRoleUser));

			JsonArray jsonArray2 = new JsonArray();

			jsonArray2.add(gson.toJson(newEnableListRole));

			JsonObject jsonObject = new JsonObject();
			jsonObject.add("updateRoleUser", jsonArray1);
			jsonObject.add("newEnableListRole", jsonArray2);
			jdBiConnector.insert(log);
			String jsonString = gson.toJson(jsonObject);
			response.getWriter().print(jsonString);
			response.getWriter().flush();

			UserUpdateEndpoint.notifyUserUpdate(idUser,"updateRole");


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

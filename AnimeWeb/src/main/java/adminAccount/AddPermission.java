package adminAccount;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import database.DAOAccounts;
import database.DAOPermission;
import database.DAORoleAccount;
import database.JDBiConnector;
import model.Account;
import model.Role;
import socket.UserUpdateEndpoint;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddPermission", value = "/admin/AddPermission")
public class AddPermission extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            int idPermission = Integer.parseInt(request.getParameter("idPermission"));
            int idUser = Integer.parseInt(request.getParameter("idUser"));
            int idRole = Integer.parseInt(request.getParameter("idRole"));
            boolean isSuccess = DAOPermission.addPermissionUser(idUser, idPermission, idRole);
            Account account = DAOAccounts.findAccountById(idUser);
            Account user = (Account) request.getSession().getAttribute("user");
            Map<String, Role> updateRoleUser = DAOAccounts.getRoleUser(idUser);
            List<Role> newEnableListRole = DAORoleAccount.getUnableAddRoles(account, user);
            Gson gson = new Gson();
            JsonArray jsonArray1 = new JsonArray();
            for (Role r : updateRoleUser.values()) {
                r.setEnablePermission(DAOPermission.getEnablePermission(idUser, r.getId()));
            }
            jsonArray1.add(gson.toJson(updateRoleUser));

            JsonArray jsonArray2 = new JsonArray();

            jsonArray2.add(gson.toJson(newEnableListRole));

            JsonObject jsonObject = new JsonObject();


            jsonObject.add("updateRoleUser", jsonArray1);
            jsonObject.add("newEnableListRole", jsonArray2);
            JDBiConnector jdBiConnector = new JDBiConnector();

            String jsonString = gson.toJson(jsonObject);

            response.getWriter().print(jsonString);
            response.getWriter().flush();
            UserUpdateEndpoint.notifyUserUpdate(idUser, "updateRole");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

package adminAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Log.Log;
import database.DAOAccounts;
import database.JDBiConnector;
import model.Account;
import model.Encode;
import security.PermissionValidate;
import services.Validation;


@WebServlet("/admin/EditPasswordAccount")
public class EditPasswordAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public EditPasswordAccount() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");



		Account user = (Account) request.getSession().getAttribute("user");
		boolean checkPermission = PermissionValidate.userHasPermission(user,"/admin/UserEdit","edit","admin",false,0,0);
		if(!checkPermission){
			response.sendError(403, "Bạn không có quyền truy cập");
			return;
		}
		String ipClient = request.getRemoteAddr();
		response.setContentType("text/html;charset=UTF-8");
		int idUser = Integer.parseInt(request.getParameter("idUser"));
		Log log = new Log(Log.ALERT, user.getId(), ipClient, "EditPassword", null, 0);
		JDBiConnector jdBiConnector = new JDBiConnector();
		Validation validate = new Validation();

		Encode encode = new Encode();
		boolean check = validate.validatePassword(password);
		String encryptPassword;
		String message;
		boolean checkEdit;
		if(check) {
			encryptPassword = encode.toSHA1(password);
			checkEdit= DAOAccounts.editPassword(idUser,encryptPassword);
			if(checkEdit) {
				log.setContent("Thay đổi mật khẩu thành công cho user có id = "+idUser);
				message ="true";
			}else {
				log.setContent("Thay đổi mật khẩu thất bại cho user có id = "+idUser);
				log.setLevel(Log.WARNING);
				message="false";
			}
		}else {
			message="error";
			log.setContent("Thay đổi mật khẩu thất bại cho user có id = "+idUser +" do sai định dạng");
		}
		jdBiConnector.insert(log);
		Gson gson = new Gson();
		response.getWriter().print(gson.toJson(message));
		response.getWriter().flush();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

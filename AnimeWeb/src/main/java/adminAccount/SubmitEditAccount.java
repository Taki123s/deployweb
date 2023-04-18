package adminAccount;

import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Log.Log;
import database.DAOAccounts;
import database.JDBiConnector;
import model.Account;
import security.PermissionValidate;
import services.ImageUpload;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // Giới hạn tối thiểu là 1MB
		maxFileSize = 1024 * 1024 * 5, // Giới hạn kích thước mỗi file là 10MB
		maxRequestSize = 1024 * 1024 * 50 // Giới hạn kích thước request là 50MB
)
@WebServlet("/admin/SubmitEditAccount")
public class SubmitEditAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public SubmitEditAccount() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Account user = (Account) request.getSession().getAttribute("user");
		boolean checkPermission = PermissionValidate.userHasPermission(user,"user","edit","admin",false,0,0);
		if(!checkPermission){
			response.sendError(403, "Bạn không có quyền truy cập");
			return;
		}
			Part file = request.getPart("imageUser");
			String fullName = request.getParameter("fullName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			int idUser = Integer.parseInt(request.getParameter("idUser"));
			int typeId = Integer.parseInt(request.getParameter("typeId"));

			String ipClient = request.getRemoteAddr();

			Log log = new Log(Log.ALERT, user.getId(), ipClient, "SubmitEditAccount", null, 0);
			JDBiConnector jdBiConnector = new JDBiConnector();

			String realPath =request.getServletContext().getRealPath("/");
			ImageUpload imgUpload = new ImageUpload();
			Date date = new Date();
			String addExtension = String.valueOf(date.getTime());


			boolean checkFileAvatar = file.getSize()>0&&file.getContentType()!=null && file.getContentType().startsWith("image/");
			String saveRootAvatar = checkFileAvatar?imgUpload.createSaveAvatarUserData(idUser, addExtension, imgUpload.getExtensionFile(file.getSubmittedFileName())):imgUpload.findImageUser(idUser, typeId);

			if(checkFileAvatar) imgUpload.saveAvatarUser(file, idUser, realPath,addExtension);


			boolean check = DAOAccounts.editUser(fullName, email, phoneNumber, saveRootAvatar, idUser);
			log.setContent("Chỉnh sửa thông tin user có id = "+idUser +"thất bại");
			if(check) {
				log.setContent("Chỉnh sửa thông tin user có id = "+idUser +"thành công");

			}
			jdBiConnector.insert(log);
			Account account = DAOAccounts.findAccountById(idUser);
			request.setAttribute("account", account);
			request.getRequestDispatcher("/admin/UserEdit").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

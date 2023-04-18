package adminAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Log.Log;
import database.JDBiConnector;
import model.Account;

/**
 * Servlet implementation class IndexAdmin
 */
@WebServlet("/admin/IndexAdmin")
public class IndexAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public IndexAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ipClient = request.getRemoteAddr();
		Account user = (Account) request.getSession().getAttribute("user");
		Log log = new Log(Log.ALERT, user.getId(), ipClient, "IndexAdmin", null, 0);
		JDBiConnector jdBiConnector = new JDBiConnector();
		log.setContent("User đăng nhập vào trang chủ admin");
		jdBiConnector.insert(log);
		request.getRequestDispatcher("/admin/Index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

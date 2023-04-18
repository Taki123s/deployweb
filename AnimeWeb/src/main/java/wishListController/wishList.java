package wishListController;

import database.DAOMovie;
import database.DaoCommentMovie;
import model.Account;
import model.CommentMovie;
import model.Movie;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "wishList", value = "/anime-main/wishList")
public class wishList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("fail");
        Account user = (Account) session.getAttribute("user");
        if(user==null) request.getRequestDispatcher("/anime-main/login.jsp").forward(request,response);

        request.getRequestDispatcher("/anime-main/wish-list.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}

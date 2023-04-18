package wishListController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DAOMovie;
import database.DaoCommentMovie;
import model.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "checkout", value = "/anime-main/checkout")
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        String action = request.getParameter("action");
        Order order = (Order) session.getAttribute("order");
        HashMap<String, WishListDetail> wishlist = (HashMap<String, WishListDetail>) session.getAttribute("wishlist");
        DAOMovie daoMovie = new DAOMovie();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        if (action == null) {
            session.removeAttribute("success");
            session.setAttribute("done","done");
            response.sendRedirect("/anime-main/CheckoutIndex");
            return;
        }

        if (action.equals("back")) {
            session.removeAttribute("order");
            response.sendRedirect("/anime-main/wishList");
        }
        if(action.equals("checkout")){
            if((user.getBalance()- order.getTotalPrice())>=0){
                for (Movie m: order.getSelectedMovies()) {
                    daoMovie.insertPurchasedMovie(user.getId(),m.getId(),m.getRenderPrice());
                    wishlist.remove(String.valueOf(m.getId()));
                    user.setBalance((user.getBalance()-order.getTotalPrice()));
                    user.setMoviesPurchased(order.getSelectedMovies());
                    daoMovie.updateBalance(user.getId(), user.getBalance());
                    session.setAttribute("wishlist",wishlist);
                    session.removeAttribute("order");
                    session.setAttribute("success",true);
                    session.removeAttribute("done");
                   response.sendRedirect("/anime-main/CheckoutIndex");

                }

            }else{
                return;
            }
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

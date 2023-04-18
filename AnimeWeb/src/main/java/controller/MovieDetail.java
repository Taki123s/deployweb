package controller;

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

@WebServlet(name = "MovieDetail", value = "/anime-main/MovieDetail")
public class MovieDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        try{
            boolean isFollow=false;
            int idMovie = Integer.parseInt(request.getParameter("idMovie"));

            Movie movie = DAOMovie.getMoviebyId(idMovie);

            int limit = 5;
            List<CommentMovie> listComment = DaoCommentMovie.getCommentMovie(idMovie,0,limit,false,0);
            movie.setListComment(listComment);
            int rendered = listComment.size();
            int availableToRender = DaoCommentMovie.availableCommentToRender(idMovie,limit,false,0);
            request.setAttribute("movie",movie);

            request.setAttribute("rendered",rendered);
            request.setAttribute("availableToRender",availableToRender);
            if(user!= null){
                int purchasedId = DAOMovie.getDetailMoviePurchased(user.getId(), idMovie);

                session.setAttribute("purchasedId",purchasedId);

                if(DAOMovie.getFollow(user.getId(),idMovie)!=0) isFollow=true;

                session.setAttribute("checkFl",isFollow);
            }

            request.getRequestDispatcher("/anime-main/anime-detail.jsp").forward(request,response);
        }catch( Exception e){
            e.printStackTrace();
            request.getRequestDispatcher("/anime-main/Index").forward(request,response);
        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

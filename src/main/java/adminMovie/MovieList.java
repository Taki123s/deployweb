package adminMovie;

import database.DAOMovie;
import model.Movie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MovieList", value = "/admin/MovieList")
public class MovieList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = request.getParameter("index")==null?0:Integer.parseInt(request.getParameter("index"));

        List<Movie> listMovie = DAOMovie.listMovieAdmin(9,index*9);
        int calcPage = DAOMovie.totalMovie()/9;
        int totalMovie = DAOMovie.totalMovie()%9==0?calcPage:calcPage+1;
        request.setAttribute("totalMovie",totalMovie);
        request.setAttribute("listMovie",listMovie);
        request.setAttribute("index",index);
        request.getRequestDispatcher("/admin/MovieList.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

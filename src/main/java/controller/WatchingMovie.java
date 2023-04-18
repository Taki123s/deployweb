package controller;

import database.DAOMovie;
import model.Movie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "WatchingMovie", value = "/anime-main/WatchingMovie")
public class WatchingMovie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String idMovie = request.getParameter("idMovie");
            HttpSession session = request.getSession();
            Movie nowMovie = (Movie) session.getAttribute("watchingMovie");
            String chapter = request.getParameter("chapter");
            Movie watchingMovie;
            if(nowMovie!=null){
                if(!String.valueOf(nowMovie.getId()).equalsIgnoreCase(idMovie)&&idMovie!=null){
                    watchingMovie= DAOMovie.watchingMovie(idMovie);
                }else{
                    watchingMovie = nowMovie;
                }
            }else{
                watchingMovie= DAOMovie.watchingMovie(idMovie);
            }
            session.setAttribute("watchingMovie",watchingMovie);
            if (chapter==null){
                chapter=watchingMovie.getFirstChapter();
            }
            request.setAttribute("chapter",chapter);

            request.setAttribute("watchChapter",watchingMovie.getListChapter().get(Integer.parseInt(chapter)-1));
            request.getRequestDispatcher("/anime-main/anime-watching.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }
}

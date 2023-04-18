package adminMovie;

import database.DAOMovie;
import model.ChapterMovie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChapterSetting", value = "/admin/ChapterSetting")
public class ChapterSetting extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idMovie = (String)request.getParameter("idMovie");

        List<ChapterMovie> chapterMovies = DAOMovie.getListChapter(idMovie);
        request.setAttribute("idMovie",idMovie);
        request.setAttribute("listChapter",chapterMovies);
        request.getRequestDispatcher("/admin/ChapterSetting.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }
}

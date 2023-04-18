package adminMovie;

import database.DAOMovie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MovieAdd", value = "/admin/MovieAdd")
public class MovieAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listType",DAOMovie.getListTypeMovie());
        request.setAttribute("listGenre", DAOMovie.listGenreHeader());
        request.getRequestDispatcher("/admin/MovieAdd.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

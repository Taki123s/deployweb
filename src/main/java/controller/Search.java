package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import database.DAOMovie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LocalDateTimeAdapter;
import model.Movie;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/anime-main/SearchByName")
public class Search extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String searchBox = request.getParameter("searchBox");
            System.out.println(searchBox);


            List<Movie> movieList = new DAOMovie().searchMovie(searchBox);
            System.out.println(movieList);


            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            String jsonString = gson.toJson(movieList);




            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(jsonString);
        } catch (Exception e
        ) {
            response.getWriter().println(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

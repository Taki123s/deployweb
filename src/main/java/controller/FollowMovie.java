package controller;

import database.DAOMovie;
import model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/anime-main/FollowMovie")
public class FollowMovie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idState = Integer.parseInt(request.getParameter("id"));
        String idMovie = request.getParameter("idmovie");
        Account user = (Account) session.getAttribute("user");
        try {
            if (user != null && idMovie != null && idState == 1) {
                DAOMovie.addFollow(user.getId(), Integer.parseInt(idMovie));
            } else if (user != null && idMovie != null && idState == 0) {
                DAOMovie.removeFollow(user.getId(), Integer.parseInt(idMovie));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
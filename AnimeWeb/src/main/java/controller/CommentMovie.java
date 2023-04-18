package controller;

import database.DAOMovie;
import database.DaoCommentMovie;
import model.Account;
import model.Movie;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CommentMovie", value = "/anime-main/CommentMovie")
public class CommentMovie extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if(user==null) request.getRequestDispatcher("/anime-main/login.jsp").forward(request,response);
       try{
           String content = request.getParameter("message");
           String idParent = request.getParameter("idParent");
           String idUserReply = request.getParameter("idUserReply");
           DaoCommentMovie daoCommentMovie = new DaoCommentMovie();
           int idMovie = Integer.parseInt(request.getParameter("idMovie"));
           boolean check = daoCommentMovie.Comment(user,idParent,idMovie,content,idUserReply);
           response.sendRedirect(getServletContext().getContextPath()+("/anime-main/MovieDetail?idMovie="+idMovie));
       }catch (Exception e){
           response.getWriter().println(e.getMessage());
       }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }
}

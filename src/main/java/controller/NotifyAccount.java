package controller;

import database.DAOAccounts;
import model.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NotifyAccount", value = "/anime-main/NotifyAccount")
public class NotifyAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        Account account = DAOAccounts.findAccountById(idUser);
        request.getSession().setAttribute("user",account);
        response.getWriter().println(account.getRoles());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

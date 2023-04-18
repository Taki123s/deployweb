package controller;

import database.DAOMovie;
import model.Movie;

import java.io.IOException;
import java.util.List;


import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet("/anime-main/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
			try {
				String indexNumber =request.getParameter("index");
				int index = indexNumber ==null?0:Integer.parseInt(indexNumber);
				String param = request.getParameter("filter")==null?"isAtoZ": request.getParameter("filter");

				boolean isAtoZ =Boolean.parseBoolean(request.getParameter("isAtoZ"));
				boolean isDescPrice =Boolean.parseBoolean(request.getParameter("isDescPrice"));
				boolean isDescDate = Boolean.parseBoolean(request.getParameter("isDescDate"));


				int calcPage = DAOMovie.totalMovie()/9;
				int totalMovie = DAOMovie.totalMovie()%9==0?calcPage:calcPage+1;
				List<Movie> renderMovies = DAOMovie.renderMovie(index*9,9,param);
				request.setAttribute("renderMovies",renderMovies);
				request.setAttribute("totalMovie",totalMovie);
				request.setAttribute("index",index);
				request.setAttribute("param",param);

				request.getRequestDispatcher("/anime-main/index.jsp").forward(request, response);
			} catch (Exception e) {
//				response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
				response.getWriter().println(e.getMessage());

			}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

package adminMovie;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.DAOMovie;
import model.ChapterMovie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "RemoveChapter", value = "/admin/RemoveChapter")
public class RemoveChapter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idChapter = request.getParameter("idChapter");
        String realPath = request.getServletContext().getRealPath("/");
        ChapterMovie chapter = DAOMovie.findChapter(Integer.parseInt(idChapter));
        File chapterFile = new File(realPath+File.separator+chapter.getName());
        chapterFile.delete();
        boolean isSuccess=DAOMovie.removeChapter(idChapter);
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("isSuccess",isSuccess);
        response.getWriter().print(gson.toJson(object));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

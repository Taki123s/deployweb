package adminMovie;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.DAOMovie;

import model.ChapterMovie;
import services.VideoUpload;
import socket.UserUpdateEndpoint;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@MultipartConfig
@WebServlet(name = "UpdateChapter", value = "/admin/UpdateChapter")
public class UpdateChapter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String index = request.getParameter("index");
        String opening = request.getParameter("opening");

        Part file = request.getPart("video-upload");
        String idMovie = request.getParameter("idMovie");
        String type = request.getParameter("type");
        if( file.getContentType()!=null && file.getContentType().startsWith("video/")){
            if(!index.equalsIgnoreCase("")&&!opening.equalsIgnoreCase("")){

                boolean checkValidChapter = !type.equalsIgnoreCase("edit") && DAOMovie.isValidChapter(idMovie, index);

                if(checkValidChapter){
                    response.setStatus(500);
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().println("Tập phim đã tồn tại, vui lòng sử dụng chức năng chỉnh sửa để thay đổi video");
                    return;
                }else{

                    String realPath =request.getServletContext().getRealPath("/");
                    Date date = new Date();
                    String addExtension = String.valueOf(date.getTime());
                    VideoUpload videoUpload = new VideoUpload();

                        String chapterFullPath = videoUpload.fullPathChapter(file, idMovie, index, realPath, addExtension);


                        file.write(chapterFullPath);
                        ChapterMovie updateChapter = null;

                        if(type.equalsIgnoreCase("insert")){
                            updateChapter= DAOMovie.addChapter(idMovie, index, opening, videoUpload.createSaveChapterMovieData(file, idMovie, index, addExtension));
                        }
                        if(type.equalsIgnoreCase("edit")){
                            String idChapter = request.getParameter("idChapter");
                            updateChapter= DAOMovie.updateChapter(idChapter, index, opening, videoUpload.createSaveChapterMovieData(file, idMovie, index, addExtension));

                        }
                        assert updateChapter!=null;
                        Gson gson = new Gson();
                        JsonObject object = new JsonObject();
                        object.addProperty("chapter",gson.toJson(updateChapter));
                        response.getWriter().print(gson.toJson(object));


                }
            }else{
                response.setStatus(500);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println("Dữ liệu tập phim và opening không được để trống");

                return;
            }
        }else{
            response.setStatus(500);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("file phải là video");

            return;
        }
    }
}

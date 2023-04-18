package adminMovie;

import database.DAOMovie;
import model.Account;
import services.ImageUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@MultipartConfig(
        fileSizeThreshold = 1024, // Giới hạn tối thiểu là 1MB
        maxFileSize = 1024 * 1024 * 10, // Giới hạn kích thước mỗi file là 10MB
        maxRequestSize = 1024 * 1024 * 50 // Giới hạn kích thước request là 50MB
)
@WebServlet(name = "SubmitAddMovie", value = "/admin/SubmitAddMovie")
public class SubmitAddMovie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
       try{
           HttpSession session = request.getSession();
           Account user = (Account) session.getAttribute("user");
           Collection<Part> parts = request.getParts();
           List<Part> listImage = new ArrayList<>();
           for(Part p : parts){
               if(p.getContentType()!=null && p.getContentType().startsWith("image/")){
                   listImage.add(p);
               }
           }
           if(listImage.size()!=5){
               request.setAttribute("error","Phải đủ 5 hình ảnh");
               request.getRequestDispatcher("/admin/MovieAdd").forward(request,response);
               return;
           }else{
               String realPath =request.getServletContext().getRealPath("/");

               Date date = new Date();
               String addExtension = String.valueOf(date.getTime());
               String name = request.getParameter("name");
               String totalEpisode = request.getParameter("totalEpisode");
               String descriptionVN = request.getParameter("descriptionVN");
               String descriptionEN = request.getParameter("descriptionEN");
               String price = request.getParameter("price");
               String[] genresValue = request.getParameterValues("genreValue");
               String typePicker = request.getParameter("typePicker");
               System.out.println("real Path" + realPath);

               ImageUpload imageUpload = new ImageUpload();

               int savedMovieId= DAOMovie.addMovie(name,totalEpisode,descriptionVN,descriptionEN,typePicker,price,genresValue);
               List<String> savedAvatar = imageUpload.saveAvatarMovie(listImage,savedMovieId,realPath,addExtension);
               DAOMovie.updateAvatarMovie(savedAvatar,savedMovieId);
           }
       }catch (Exception  e){
           e.printStackTrace();
       }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }
}

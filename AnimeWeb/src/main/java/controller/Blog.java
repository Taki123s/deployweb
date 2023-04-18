//package controller;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//
//@WebServlet("/anime-main/gotoblog")
//public class Blog extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        String idBlog = request.getParameter("idBlog");
//        Blog blog = listBlog.findBlogById(Integer.parseInt(idBlog));
//        if(blog!=null) {
//            String contentBlog;
//            String appPath = request.getServletContext().getRealPath("");
//            String savePath = appPath +File.separator +"anime-main\\storage\\blogSave\\"+blog.getFolder()+"\\htmlfile.html";
//
//            File ips  = new File(savePath);
//            Document doc = Jsoup.parse(ips,"UTF-8");
//            contentBlog=doc.select("body").first().children().toString();
//            contentBlog=contentBlog.replaceAll("Evaluation Only. Created with Aspose.Words. Copyright 2003-2011 Aspose Pty Ltd.", "");
//            contentBlog=contentBlog.replaceAll("htmlfile.", "storage/blogSave/"+blog.getFolder()+"/htmlfile.");
//            request.setAttribute("currentBlog", blog);
//            request.setAttribute("contentBlog", contentBlog);
//            request.getRequestDispatcher("/anime-main/blog-details.jsp").forward(request, response);
//        }else {
//            response.getWriter().println("<img class=\"rsImg\" src=\"/AnimeWeb/error.png"+"\">");
//        }
//    }
//
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        doGet(request, response);
//    }
//
//}

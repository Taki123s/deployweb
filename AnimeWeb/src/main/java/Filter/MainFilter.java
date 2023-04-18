package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns={"/*"} ,asyncSupported = true)
public class MainFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res =(HttpServletResponse) response;
//        String lang = request.getParameter("lang");
//        String url = req.getRequestURL().toString();
//        req.setCharacterEncoding("UTF-8");
//        if(lang!=null) {
//            req.getSession().setAttribute("LANG", lang);
//        }
//        boolean check = url.endsWith(".jsp") && !url.endsWith("login.jsp") && !url.endsWith("signup.jsp");
//        if(check) {
//
//            res.sendRedirect(req.getContextPath()+"/anime-main/Index");
//            return;
//        }
        chain.doFilter(request, response);
    }
}

package Filter;

import model.Account;
import security.PermissionValidate;
import security.PublicResource;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

@WebFilter(urlPatterns = {"/*"},asyncSupported = true)
public class SecurityFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//
//        if (!path.contains(".") && !PublicResource.PUBLIC_RESOURCES.containsValue(path)) {
//            System.out.println(path);
//
//            Account account = (Account) httpRequest.getSession().getAttribute("user");
//            if (account == null) {
//                httpResponse.sendRedirect(httpRequest.getContextPath() + PublicResource.PUBLIC_RESOURCES.get("Index"));
//                return;
//            } else {
//                StringTokenizer token = new StringTokenizer(httpRequest.getServletPath(), "/");
//                String externalResource = token.nextToken();
//
//                String action = httpRequest.getParameter("action");
//                boolean checkPermission = PermissionValidate.checkPermissionAction(account, httpRequest.getServletPath(), action, externalResource);
//                if (!checkPermission) {
//                    httpResponse.sendError(403, "Bạn không có quyền truy cập");
//                    return;
//                }
//            }
//
//
//        }

        chain.doFilter(request, response);
    }

}


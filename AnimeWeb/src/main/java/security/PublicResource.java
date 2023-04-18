package security;

import java.util.HashMap;
import java.util.Map;

public class PublicResource {
    public static final Map<String,String> PUBLIC_RESOURCES = new HashMap<String,String>();

    static {
        PUBLIC_RESOURCES.put("Index","/anime-main/Index");
        PUBLIC_RESOURCES.put("loginPage","/anime-main/login.jsp");
        PUBLIC_RESOURCES.put("signupPage","/anime-main/signup.jsp");
        PUBLIC_RESOURCES.put("loginServlet","/anime-main/login");
        PUBLIC_RESOURCES.put("logoutServlet","/anime-main/logOut");
        PUBLIC_RESOURCES.put("MovieDetail","/anime-main/MovieDetail");
        PUBLIC_RESOURCES.put("RenderCommentMovie","/anime-main/RenderCommentMovie");
        PUBLIC_RESOURCES.put("ValidateRegister","/anime-main/ValidateRegister");
        PUBLIC_RESOURCES.put("Register","/anime-main/Register");
        PUBLIC_RESOURCES.put("loginWithFacebook","/anime-main/loginWithFacebook");
        PUBLIC_RESOURCES.put("login-google","/anime-main/login-google");
        PUBLIC_RESOURCES.put("/","");
        PUBLIC_RESOURCES.put("notifyAccount","/anime-main/NotifyAccount");
        PUBLIC_RESOURCES.put("obServer","/observer");
        PUBLIC_RESOURCES.put("LogoutAccount","/anime-main/LogOutAccount");

    }

    public static void main(String[] args) {
        System.out.println(PUBLIC_RESOURCES);
    }
}

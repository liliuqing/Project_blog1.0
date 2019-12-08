package User;

import Dao.UserDaoImp;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String imageText = request.getParameter("image");
        String text = (String) request.getSession().getAttribute("text");
        String email=request.getParameter("email");
        String password = request.getParameter("password");
        if (!text.equalsIgnoreCase(imageText)) {
            //request.setAttribute("imageMess", "验证码输入错误!");
            request.getRequestDispatcher("/login_fail.jsp").forward(request, response);
        }
        else{
            // 获取用户名和密码

            User user= new UserDaoImp().Login(email,password);
            //if(canContinue==0) {
                // request.setAttribute("error", "用户名或密码错误!");
                request.getRequestDispatcher("/login_fail.jsp").forward(request, response);
         //   }
          //  if(canContinue==1) {
                //管理员
                response.getWriter().write("manager...");
         //   }
           // if(canContinue==2) {
                //用户
                request.getSession().setAttribute("id",user.getId());
                request.getSession().setAttribute("username",user.getUsername());
                request.getRequestDispatcher("/index_user.jsp").forward(request,response);
        //    }
        }
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

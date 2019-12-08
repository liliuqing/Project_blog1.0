package User;

import Dao.UserDao;
import Dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/regist")
public class Regist extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String againPwd=request.getParameter("againPwd");
        String username=request.getParameter("username");
        String age=request.getParameter("age");
        String sex=request.getParameter("sex");
        UserDao dao = new UserDaoImp();
        boolean canContinue=false;
        if(password.equals(againPwd)) {
            canContinue = dao.judgeRegist(email, username);
            if(canContinue==true)
            {
                dao.Regist(email,password,username,age,sex);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
            else {
                //注册不成功
                request.getRequestDispatcher("/regist_fail.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

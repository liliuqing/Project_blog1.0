package User;

import Dao.UserDao;
import Dao.UserDaoImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backpwd")
public class BackPwd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //邮箱验证码验证 验证通过之后 修改密码
        String password=request.getParameter("password");
        String againpwd=request.getParameter("againpwd");
        int id=(int) request.getSession().getAttribute("id");
        UserDao dao =new UserDaoImp();
        if(password.equals(againpwd)){
            dao.ChageInfo(id,"password",password);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        else
            request.getRequestDispatcher("/backPwd_fail.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

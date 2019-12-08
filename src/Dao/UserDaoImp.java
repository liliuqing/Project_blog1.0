package Dao;


import Util.JDBCUtil;
import Util.StringUtil;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImp implements UserDao{
        public User Login(String email, String password){
            String sql = "select *  from blog_user " + " where email='" + email + "' and password='" + StringUtil.getMd5(password) + "'";
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                con = JDBCUtil.getCon();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next()){
                    User user = new User();
                    user.setId( rs.getInt("id") );
                    user.setUsername( rs.getString("username") );
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtil.close(con, st, rs);
            }
            return null;
        }
        public boolean judgeRegist(String email,String username){
            String sql = "select email,username from blog_user ";
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                con = JDBCUtil.getCon();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next()){
                    if(email.equals(rs.getString("email")))
                        return false;
                    else if(username.equals(rs.getString(username)))
                        return false;
                    else
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtil.close(con, st, rs);
            }
            return true;
        }

        private void innerExecuteSql(String sql){
        Connection con = null;
        Statement st = null;
        try {
            con = JDBCUtil.getCon();
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(con, st);
        }
    }
    public void Regist(String email,String password,String username,String age,String sex){
        String sql = "insert into blog_user values(seq_blog_userid.nextval, '"+email+"','"+StringUtil.getMd5(password)+"','"+username+"','"+age+"','"+sex+"')";
        innerExecuteSql(sql);
    }
        public void ChageInfo(int id,String key,String values){
            String sql="update blog_user set "+key+" = '"+values+"' where id = "+id;
            innerExecuteSql(sql);
        }
//        public void ChangePwd(int id,String password){
//            String sql="update blog_user set password = '"+StringUtil.getMd5(password)+"' where id = "+id;
//            innerExecuteSql(sql);
//        }
//        public void ChangeUsername(int id,String username){
//            String sql="update blog_user set username = '"+username+"' where id = "+id;
//            innerExecuteSql(sql);
//        }
//        public void ChangeAge(int id,String age){
//            String sql="update blog_user set age = '"+age+"' where id = "+id;
//            innerExecuteSql(sql);
//        }
//        public void ChangeSex(int id,String sex){
//            String sql="update blog_user set sex = '"+sex+"' where id = "+id
//            innerExecuteSql(sql);
//        }
}

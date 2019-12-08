package Dao;

import Util.JDBCUtil;
import entity.Blog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogDaoImp implements BlogDao{
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
    public void add(String title, String type, String content, Date createTime, int userid, String imgUrl){
        String sql = "insert into blog_blogger values(seq_blog_blogid.nextval, '" + title + "','"+type+"','" + content + "',sysdate,'" + userid+ "','" + imgUrl + "')";
        innerExecuteSql(sql);
    }
    public void delete(int id){
        String sql="delete from blog_blogger where id="+id;
        innerExecuteSql(sql);
    }
    public void update(String title,String content,int id){
        String sql="update blog_blogger set title = ' "+title+" ', content= ' "+content+"' where id="+id;
        innerExecuteSql(sql);
    }
    public List<Blog> viewAll(){
        //创建一个装Blog对象的List容器
        List<Blog> blogList = new ArrayList();


        String sql = "select b.id bid,title,type,content,imgUrl, b.createtime btime, username" +
                " from blog_blogger b, blog_user u " +
                " where b.id=u.userid order by b.createTime desc";

        Connection con =  null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con =JDBCUtil.getCon();
            st = con.createStatement();

            rs = st.executeQuery(sql);
            while(rs.next()){

                //一旦获取到一条数据就创建一个Blog对象
                Blog blog = new Blog();
                //将该条数据的列设置到Blog对象中封装起来
                blog.setId( rs.getInt("bid") );
                blog.setTitle( rs.getString("title") );
                blog.setType(rs.getString("type"));
                blog.setContent(rs.getString("content"));
                blog.setCreateTime( rs.getTimestamp("createTime") );
                blog.setUsername( rs.getString("username") );
                blog.setImgUrl( rs.getString("imgUrl") );

                //将Blog对象放进List容器
                blogList.add(blog);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(con, st, rs);
        }
        //返回List容器，里面放的就是查询出来的所有Blog数据
        return blogList;
    }

    public Blog viewById(int id){
        String sql = "select * from blog_blogger where id=" + id;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = JDBCUtil.getCon();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                //查到ID为指定ID的博客信息
                Blog blog = new Blog();
                blog.setId( rs.getInt("id") );
                blog.setTitle( rs.getString("title") );
                blog.setContent( rs.getString("content") );
                return blog;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(con, st, rs);
        }
        return null;
    }

}

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

public interface BlogDao {
    public void add(String title, String type, String content, Date createTime, int userid, String imgUrl);
    public void delete(int id);
    public void update(String title,String content,int id);
    public List<Blog> viewAll();
    public Blog viewById(int id);
}

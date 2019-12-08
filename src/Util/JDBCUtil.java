package Util;

import java.sql.*;

/**
 * JDBC工具类
 */
public class JDBCUtil {

    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String USER_NAME = "dyl";
    public static final String PASS = "dyl";

    static {
        //加载驱动
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getCon() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER_NAME, PASS);
        return con;
    }

    //释放资源
    public static void close(Connection con, Statement st){
        close(con, st, null);
    }

    public static void close(Connection con,
                             Statement st, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

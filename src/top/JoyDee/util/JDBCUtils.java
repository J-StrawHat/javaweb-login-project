package top.JoyDee.util;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

// JDBC工具类，使用Durid连接池
public class JDBCUtils {
    private static DataSource ds;

    static {
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            //使用ClassLoader加载配置文件，获取字节输入流
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL resurl = classLoader.getResource("druid.properties");
            String path = resurl.getPath();


            //InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            //if(is == null) System.out.println("null!!!!");
            pro.load(new FileReader(path));
            //2. 初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //获取连接池对象
    public static DataSource getDataSource(){
        return ds;
    }
    //获取连接 Connection 对象
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
}

package top.JoyDee.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.JoyDee.domain.User;
import top.JoyDee.util.JDBCUtils;

//操作数据库中User表的类
public class UserDao {
    //声明 JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * 登陆的方法
     * @param loginUser 只有用户名和密码
     * @return user 包含用户的【全部】数据；但若查询不到，则返回null
     */
    public User login(User loginUser){
        try {
            //1. 编写SQL
            String sql = "SELECT * FROM userlist WHERE username = ? AND password = ?";
            //2. 调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());

            return user;
        } catch (DataAccessException e){
            e.printStackTrace(); //后期应该记录在日志
            return null;
        }

    }
}

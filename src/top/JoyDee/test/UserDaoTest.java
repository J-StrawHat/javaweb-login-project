package top.JoyDee.test;

import org.junit.Test;
import top.JoyDee.domain.User;
import top.JoyDee.dao.UserDao;

public class UserDaoTest {
    @Test
    public void testLogin(){
        User loginuser = new User("Luffy", "66666");
        UserDao dao = new UserDao();
        User user = dao.login(loginuser);
        System.out.println(user);
    }
}

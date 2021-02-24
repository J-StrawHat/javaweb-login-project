package top.JoyDee.test;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import top.JoyDee.domain.User;

import java.lang.reflect.InvocationTargetException;


public class BeanUtilsTest {
    @Test
    public void test(){
        User user = new User();
        try {
            BeanUtils.setProperty(user, "password", "123456");
            BeanUtils.setProperty(user, "username", "Zoro");
            System.out.println(user);
            //输出为：User{id=0, username='Zoro', password='123456'}

            String password = BeanUtils.getProperty(user, "password");
            System.out.println(password); //输出为：123456

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

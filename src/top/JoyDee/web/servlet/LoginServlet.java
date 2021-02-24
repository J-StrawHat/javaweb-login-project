package top.JoyDee.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import top.JoyDee.dao.UserDao;
import top.JoyDee.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 设置编码
        request.setCharacterEncoding("utf-8");
        /* User loginUser = new User(
                request.getParameter("username"),
                request.getParameter("password")); */
        //2. 获取所有请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //3.1 创建 User 对象
        User loginUser = new User();
        //3.2 使用 BeanUtils 封装
        try {
            BeanUtils.populate(loginUser, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        User resUser = new UserDao().login(loginUser);
        if(resUser == null){ //登陆失败
            request.getRequestDispatcher("/failServlet").forward(request, response);
        }
        else {
            request.setAttribute("user", resUser);
            request.getRequestDispatcher("/successServlet").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

# 用户登录案例

大部分源码及思路，参考了B站上传的[传智播客教学视频](https://www.bilibili.com/video/BV1uJ411k7wy?p=709&spm_id_from=pageDriver)，十分感谢！

本项目中运用的知识点、技术，较为综合。放在仓库的目的，是为了让菜鸟的我**更好地温故** JavaWeb 中的 Servlet 相关API及相应知识点。😅

### 任务需求

1. 编写 `login.html` 登录页面，包含两个用户输入框：`username` 与 `passwo；rd`。
2. 登录页面中，如果用户：
   + 登录成功，则跳转到 `SuccessServlet` ，页面展示为：登录成功！`用户名`，欢迎您！
   + 登录失败，则跳转到 `FailServlet` ，页面展示为：登录失败，用户名或密码错误
3. 使用 Druid **数据库连接池**技术，操作 MySQL中已经创建好的 `userlist` 表；
4. 使用 **JdbcTemplate 技术**封装 JDBC；

### 页面效果

用户输入**已注册好**的信息：

<img src="https://gitee.com/j__strawhat/MyImages/raw/master/20210223222436.png"/>

登录成功：

<img src="https://gitee.com/j__strawhat/MyImages/raw/master/20210223222448.png"/>

登录失败：

<img src="https://gitee.com/j__strawhat/MyImages/raw/master/20210223222508.png"/>

### 环境配置

+ Java EE version：Java EE 7
+ Tomcat：8.5.63
+ Servlet：3.1
+ MySQL：5.7.30

### 附加

#### 一

预先设计好的数据库

```sql
SHOW DATABASES;

CREATE DATABASE mydb;
USE mydb;

CREATE TABLE userlist(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(32) UNIQUE NOT NULL,
	PASSWORD VARCHAR(32) NOT NULL
);

DESC userlist;

INSERT INTO userlist VALUES(NULL, 'Luffy', '66666');
SELECT * FROM userlist;
```

<img src="https://gitee.com/j__strawhat/MyImages/raw/master/20210223182146.png"/>

并增加一条数据：

<img src="https://gitee.com/j__strawhat/MyImages/raw/master/20210223182409.png"/>


#### 二

源代码中使用了 `BeanUtils` 简化封装 JavaBean

在 `src\top\JoyDee\web\servlet\LoginServlet.java` 中：

```java
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
```


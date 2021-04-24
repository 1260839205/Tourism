package com.aguo.test;

import com.aguo.tourism.dao.UserDao;
import com.aguo.tourism.dao.impl.UserDaoImpl;
import com.aguo.tourism.domain.User;
import com.aguo.tourism.service.UserService;
import com.aguo.tourism.service.impl.UserServiceImpl;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/21 上午9:42
 */
public class Test {

    /**
     * JdbcUtils测试类
     * 测试功能：登陆数据获取
     */
    @org.junit.jupiter.api.Test
    public void testJdbcUtils(){
        UserDao ud = new UserDaoImpl();
        User user = ud.loginUser("1260839205", "aguo123456");
        System.out.println(user);
    }

    /**
     * UserService测试
     * 测试功能：登陆是否成功返回值
     */
    @org.junit.jupiter.api.Test
    public void testUserServiceLogin(){
        UserService us = new UserServiceImpl();
        User loginFlag = us.loginUser("1260839205", "aguo12345");
        if (loginFlag != null){
            System.out.println("登陆成功。。。。");
        }else{
            System.out.println("登陆失败，账号或密码错误。。。。");
        }
    }

    /**
     * 注册功能的测试
     */
    @org.junit.jupiter.api.Test
    public void testRegister(){
        User user = new User(0,"2801397389","2801397389","张三","2021-4-30","男","13409688162","2801397389@qq.com","a","a");
        UserDaoImpl ud = new UserDaoImpl();
        boolean a = ud.userAdd(user);
        System.out.println(a);
    }

    /**
     * 用户名校验
     */
    @org.junit.jupiter.api.Test
    public void testUsername() {
        UserDao ud = new UserDaoImpl();
        boolean flag = ud.userNameCheck("12606839205");
        System.out.println(flag);
    }
}

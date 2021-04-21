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
        boolean loginFlag = us.loginUser("1260839205", "aguo12345");
        if (loginFlag){
            System.out.println("登陆成功。。。。");
        }else{
            System.out.println("登陆失败，账号或密码错误。。。。");
        }
    }
}

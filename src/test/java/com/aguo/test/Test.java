package com.aguo.test;

import com.aguo.tourism.dao.CategoryDao;
import com.aguo.tourism.dao.RouteDao;
import com.aguo.tourism.dao.UserDao;
import com.aguo.tourism.dao.impl.CategoryDaoImpl;
import com.aguo.tourism.dao.impl.RouteDaoImpl;
import com.aguo.tourism.dao.impl.UserDaoImpl;
import com.aguo.tourism.domain.Category;
import com.aguo.tourism.domain.User;
import com.aguo.tourism.service.UserService;
import com.aguo.tourism.service.impl.UserServiceImpl;
import com.aguo.tourism.utils.JedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

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
        ObjectMapper om = new ObjectMapper();
        String user_json = null;
        try {
            user_json = om.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(user_json);
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

    @org.junit.jupiter.api.Test
    public void test(){
        System.out.println("\\");
    }

    @org.junit.jupiter.api.Test
    public void testCategorys(){
        CategoryDao cd = new CategoryDaoImpl();

        List<Category> categorys = cd.getCategory();
        for (Category category : categorys) {
            System.out.println(category.getCname());
        }
        ObjectMapper om = new ObjectMapper();
        String str = null;
        try {
            str = om.writeValueAsString(categorys);
            System.out.println(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Jedis jedis = JedisUtils.getJedisPool();
        jedis.set("category",str);
    }

    @org.junit.jupiter.api.Test
    public void testRoute(){
        RouteDao rd = new RouteDaoImpl();
        int count = rd.getCount(5);
        System.out.println(count);
    }
}

package com.aguo.tourism.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/27 下午7:40
 */

public class BaseServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求路径
        String uri = request.getRequestURI();
        System.out.println("请求路径为："+uri);

        //切割路径，找到该路径要使用的方法
        System.out.println(uri.substring(uri.lastIndexOf("/") + 1));
        System.out.println("请求路径的资源为："+this);
        try {
            Method method = this.getClass().getMethod(uri.substring(uri.lastIndexOf("/") + 1), HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

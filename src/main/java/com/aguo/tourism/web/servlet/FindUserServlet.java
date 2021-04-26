package com.aguo.tourism.web.servlet;

import com.aguo.tourism.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/26 下午8:38
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询域中是否有用户数据
        User user = (User) request.getSession().getAttribute("user");

        System.out.println("获取到："+user);
        ObjectMapper om = null;
        //有信息则封装到json中
        if (user != null){
            om = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            om.writeValue(response.getWriter(),user);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

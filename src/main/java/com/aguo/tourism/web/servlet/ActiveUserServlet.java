package com.aguo.tourism.web.servlet;

import com.aguo.tourism.service.UserService;
import com.aguo.tourism.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/25 下午8:10
 */
@WebServlet(name = "ActiveUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置接收参数的编码
        request.setCharacterEncoding("utf-8");

        //获取用户激活的uuid
        String code = request.getParameter("code");

        //首先判断code是否为空
        if (code != null){
            //code 存在
            UserService us = new UserServiceImpl();
            boolean flag = us.active(code);
            if (flag){
                response.getWriter().write("恭喜您激活成功！请<a href='login.html'>登陆</a>");
            }else {
                response.getWriter().write("很抱歉激活失败，请联系管理员！");
            }
        }else {
            //code 不存在
            request.getRequestDispatcher("index.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

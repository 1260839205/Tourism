package com.aguo.tourism.web.servlet;

import com.aguo.tourism.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/27 上午10:13
 */
@WebServlet("/exitServlet")
public class ExitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session域中的用户信息
        User user = (User) request.getSession().getAttribute("user");

        //判断是否存在登陆的用户
        if (user != null){
            //如果存在就清除登陆信息
            request.getSession().removeAttribute("user");

            //或者销毁session
            request.getSession().invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

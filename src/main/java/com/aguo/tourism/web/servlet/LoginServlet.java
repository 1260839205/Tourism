package com.aguo.tourism.web.servlet;

import com.aguo.tourism.domain.User;
import com.aguo.tourism.service.UserService;
import com.aguo.tourism.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/21 上午11:11
 */

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置接收参数的编码
        request.setCharacterEncoding("utf-8");
        UserService us = null;
        Map<String,Object> map = new HashMap<String,Object>();
        ObjectMapper om = new ObjectMapper();
        User user = null;
        boolean flag = false , err = false , login = true;

        //获取用户登陆的账号密码以及验证码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String check = request.getParameter("check");
        String checkCode = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        request.removeAttribute("CHECKCODE_SERVER");
        response.setContentType("application/json;charset=utf-8");

        if (checkCode != null && !"".equals(check) && check != null && checkCode.equalsIgnoreCase(check)){
            if (username != null && password != null && !"".equals(username) && !"".equals(password)){
                us = new UserServiceImpl();
                user = us.loginUser(username, password);
                if (user != null){
                    login = false;
                }
            }else {
                flag = false;
            }
        }else {
            err = true;
            flag = false;
        }

        if (err) {
            map.put("errorMsg","验证码输入错误！");
        }else if (login){
            map.put("errorMsg","账号或密码错误！");
        }else if (!(user.getStatus().equals("Y"))){
            us.checkEmail(user);
            map.put("errorMsg","您尚未激活，已发送邮件到您的邮箱，请及时激活");
        }else {
            flag = true;
            request.getSession().setAttribute("user",user);
        }
        map.put("flag",flag);
        om.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

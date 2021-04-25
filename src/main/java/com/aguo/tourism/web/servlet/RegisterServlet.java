package com.aguo.tourism.web.servlet;

import com.aguo.tourism.domain.User;
import com.aguo.tourism.service.UserService;
import com.aguo.tourism.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/24 上午10:36
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置接收编码的格式
        request.setCharacterEncoding("utf-8");

        //创建业务逻辑操作对象
        UserService us;

        //将浏览器请求的值存入Map集合
        Map parameterMap = request.getParameterMap();

        //在request域中获取真实的验证码值
        String checkCode = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        System.out.println("真实验证码为："+checkCode);
        request.removeAttribute("CHECKCODE_SERVER");

        //创建反馈信息给前台的map对象
        Map<String,Object> map = new HashMap<String, Object>();

        System.out.println("1："+checkCode);
        System.out.println(request.getParameter("check"));
        response.setContentType("application/json;charset=utf-8");
        //首先判断验证码是否正确，若不正确则不允许通过
        if (checkCode != null && checkCode.equalsIgnoreCase(request.getParameter("check"))){
            User user = new User();

            try {
                BeanUtils.populate(user,parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println(user);

            us = new UserServiceImpl();
            boolean flag = us.userAdd(user);

            if (!flag){
                map.put("flag",false);
                map.put("errorMsg","注册失败，用户名重复");
            }else {
                map.put("flag",true);
                map.put("errorMsg","注册成功");
            }
        }else {
            map.put("flag",false);
            map.put("errorMsg","验证码输入错误");
        }
        ObjectMapper om = new ObjectMapper();
        om.writeValue(response.getWriter(),map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

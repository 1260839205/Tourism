package com.aguo.tourism.web.servlet.userservlet;

import com.aguo.tourism.domain.User;
import com.aguo.tourism.service.UserService;
import com.aguo.tourism.service.impl.UserServiceImpl;
import com.aguo.tourism.web.servlet.UserBaseServlet;
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
 * @Date 2021/4/27 下午7:42
 */
@WebServlet("/user/*")
public class UserServlet extends UserBaseServlet {
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("wozhixing了");
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

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置接收参数的编码
        request.setCharacterEncoding("utf-8");

        //获取用户激活的uuid
        String code = request.getParameter("code");

        String msg = null;

        //首先判断code是否为空
        if (code != null){
            //code 存在
            UserService us = new UserServiceImpl();
            boolean flag = us.active(code);
            if (flag){
                msg = "恭喜您激活成功！请<a href='login.html'>登陆</a>";
            }else {
                msg = "很抱歉激活失败，请联系管理员！";
            }
        }else {
            //code 不存在
            response.sendRedirect(request.getContextPath()+"/index.html");
        }
//        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(msg);
    }
}

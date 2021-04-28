package com.aguo.tourism.web.servlet.category;

import com.aguo.tourism.domain.Category;
import com.aguo.tourism.service.CategoryService;
import com.aguo.tourism.service.impl.CategoryServiceImpl;
import com.aguo.tourism.web.servlet.BaseServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/28 上午10:02
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    public void findNavigation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建实现业务的对象
        CategoryService cs = new CategoryServiceImpl();

        //调用方法查询导航栏的信息
        List<Category> categorys = cs.getCategory();

        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(om.writeValueAsString(categorys));
    }
}

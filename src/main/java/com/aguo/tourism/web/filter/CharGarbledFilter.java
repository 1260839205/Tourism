package com.aguo.tourism.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/25 下午4:19
 */
@WebFilter(filterName = "CharGarbledFilter")
public class CharGarbledFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将父接口转化为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //将一切请求的编码格式都设置为UTF-8
        request.setCharacterEncoding("utf-8");

        //将一切响应的编码格式也都设置为UTF-8
        response.setCharacterEncoding("text/html;charset=utf-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

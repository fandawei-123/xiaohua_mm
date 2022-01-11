package com.huahua.web.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 字符集过滤器
 *
 * @author 小花
 * @Company 小花时代
 */
@Component
//@WebFilter(value = {"/*"})
public class AuthorFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * 初始化方法，获取过滤器的配置对象
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession session;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) resp;
            session = request.getSession();
            //获取本次操作
            String url = request.getRequestURI();

            url = url.substring(1);
            if (url.endsWith(".css")
                    || url.endsWith(".js")
                    || url.endsWith(".png")
                    || url.endsWith(".jpg")
                    || url.endsWith("index.jsp")
                    || url.endsWith("login.jsp")
                    || url.endsWith("unauthorized.jsp")
            ){
                chain.doFilter(request, response);
                return;
            }
            //获取本次操作的参数
            String queryString = request.getQueryString();
            if(queryString.endsWith("operation=login")
                    || queryString.endsWith("operation=home")
            ) {
                chain.doFilter(request, response);
                return;
            }
            int index = queryString.indexOf('&');
            if (index != -1) {
                queryString = queryString.substring(0, index);
            }
            url = url + "?" + queryString;

            String authorStr = session.getAttribute("authorStr").toString();
            if (authorStr.contains(url)) {
                //6.放行
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        //可以做一些清理操作
    }
}

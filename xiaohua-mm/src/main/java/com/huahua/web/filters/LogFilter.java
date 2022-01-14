package com.huahua.web.filters;

import com.huahua.domain.system.SysLog;
import com.huahua.domain.system.User;
import com.huahua.service.system.SysLogService;
import com.huahua.service.system.impl.SysLogServiceImpl;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符集过滤器
 *
 * @author 小花
 * @Company 小花时代
 */
@Component
//@WebFilter(value = {"/*"})
public class LogFilter implements Filter {

    private FilterConfig filterConfig;
    private SysLogService sysLogService;
    public SysLog sysLog;

    /**
     * 初始化方法，获取过滤器的配置对象
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        sysLogService = new SysLogServiceImpl();
        sysLog = new SysLog();
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

            //获取本次操作的参数
            String queryString = request.getQueryString();
            if (queryString != null) {
                int index = queryString.indexOf('&');
                if (index != -1) {
                    queryString = queryString.substring(0, index);
                }
            }
            url = url + "?" + queryString;
            User loginUser = (User) session.getAttribute("loginUser");
            if (loginUser != null) {
                String userName = loginUser.getUserName();

                if (url.contains("system/") || url.contains("store/") || url.contains("front/")) {
                    String ip4 = Inet4Address.getLocalHost().getHostAddress();
                    request.getRemoteAddr();
                    String method = url;
                    String action = "http://" + ip4 + "/" + url;
                    sysLog.setIp(ip4);
                    sysLog.setMethod(method);
                    sysLog.setAction(action);
//                    sysLogService.save(sysLog);
//                    doSaveLog(request, response, sysLog,url);
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doSaveLog(HttpServletRequest request, HttpServletResponse response, SysLog sysLog,String url) throws IOException {
        request.getSession().setAttribute("sysLog", sysLog);
        response.sendRedirect(request.getContextPath() + "/system/sysLog?operation=saveLog&url="+url);
    }


    @Override
    public void destroy() {
        //可以做一些清理操作
    }
}

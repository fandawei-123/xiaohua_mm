package com.huahua.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.system.SysLog;
import com.huahua.utils.BeanUtil;
import com.huahua.web.controller.BaseServlet;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Huahua
 */
@WebServlet("/system/sysLog")
@Component
public class SysLogServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (list.equals(operation)) {
            try {
                this.list(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (delete.equals(operation)) {
            try {
                this.delete(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } /*else if ("saveLog".equals(operation)){
            try {
                this.saveLog(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }

//    private void saveLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        SysLog sysLog = (SysLog) request.getSession().getAttribute("sysLog");
//        String url = request.getParameter("url");
//        System.out.println(url);
//        sysLogService.save(sysLog);
//        response.sendRedirect(request.getContextPath() + "/" + url);
//    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //进入列表页
        //获取数据
        int page = 1;
        int size = 5;
        page = toPage(request, page);
        size = toSize(request, size);
        PageInfo all = sysLogService.findAll(page, size);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/syslog/list.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //将数据获取到，封装成一个对象
        SysLog sysLog = BeanUtil.fillBean(request, SysLog.class);
        //调用业务层接口update
        sysLogService.delete(sysLog);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/system/sysLog?operation=list");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

//uri:/system/sysLog?operation=list

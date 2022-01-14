package com.huahua.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.store.ExamineLog;
import com.huahua.utils.BeanUtil;
import com.huahua.web.controller.BaseServlet;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//uri:/store/examineLog?operation=list

/**
 * @author Huahua
 */
@WebServlet("/store/examineLog")
@Component
public class ExamineLogServlet extends BaseServlet {

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
        } else if ("toCheck".equals(operation)){
            try {
                this.toCheck(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //进入列表页
        //获取数据
        int page = 1;
        int size = 5;
        page = toPage(request, page);
        size = toSize(request, size);
        PageInfo all = examineLogService.findAll(page, size);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/examineLog/list.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //将数据获取到，封装成一个对象
        ExamineLog examineLog = BeanUtil.fillBean(request, ExamineLog.class);
        //调用业务层接口update
        examineLogService.delete(examineLog);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/store/examineLog?operation=list");
    }

    private void toCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ExamineLog examineLog = examineLogService.findById(id);
        request.setAttribute("examineLog", examineLog);
        request.getRequestDispatcher("/WEB-INF/pages/store/examineLog/information.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

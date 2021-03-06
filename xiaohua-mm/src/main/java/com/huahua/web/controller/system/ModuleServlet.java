package com.huahua.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.system.Module;
import com.huahua.utils.BeanUtil;
import com.huahua.web.controller.BaseServlet;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author Huahua
 */
@SuppressWarnings("all")
@WebServlet("/system/module")
@Component
public class ModuleServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (list.equals(operation)) {
            try {
                this.list(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (toAdd.equals(operation)) {
            try {
                this.toAdd(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (save.equals(operation)) {
            try {
                this.save(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (toEdit.equals(operation)) {
            try {
                this.toEdit(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (edit.equals(operation)) {
            try {
                this.edit(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (delete.equals(operation)) {
            try {
                this.delete(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {

        //???????????????
        //????????????
        int page = 1;
        int size = 10;
        page = toPage(request,page);
        size = toSize(request,size);
        PageInfo all = moduleService.findAll(page, size);
        //?????????????????????????????????
        request.setAttribute("page", all);
        //????????????
        request.getRequestDispatcher("/WEB-INF/pages/system/module/list.jsp").forward(request, response);
    }


    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        List<Module> all = moduleService.findAll();
        request.setAttribute("moduleList", all);
        request.getRequestDispatcher("/WEB-INF/pages/system/module/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //??????????????????????????????????????????
        Module module = BeanUtil.fillBean(request, Module.class, "yyyy-MM-dd");
        //?????????????????????save
        moduleService.save(module);
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");

    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        List<Module> all = moduleService.findAll();
        request.setAttribute("moduleList", all);
        //????????????????????????
        String id = request.getParameter("id");
        Module module = moduleService.findById(id);
        //??????????????????????????????????????????????????????
        request.setAttribute("module", module);
        //????????????
        request.getRequestDispatcher("/WEB-INF/pages/system/module/update.jsp").forward(request, response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //??????????????????????????????????????????
        Module module = BeanUtil.fillBean(request, Module.class, "yyyy-MM-dd");
        //?????????????????????update
        moduleService.update(module);
        //??????????????????list
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //??????????????????????????????????????????
        Module module = BeanUtil.fillBean(request, Module.class);
        //?????????????????????update
        moduleService.delete(module);
        //??????????????????list
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


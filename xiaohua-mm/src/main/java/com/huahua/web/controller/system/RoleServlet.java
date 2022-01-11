package com.huahua.web.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.huahua.domain.system.Role;
import com.huahua.utils.BeanUtil;
import com.huahua.web.controller.BaseServlet;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author Huahua
 */
@WebServlet("/system/role")
@Component
public class RoleServlet extends BaseServlet {


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
        } else if ("author".equals(operation)) {
            try {
                this.author(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("updateRoleModule".equals(operation)) {
            try {
                this.updateRoleModule(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //进入列表页
        //获取数据
        int page = 1;
        int size = 5;
        page = toPage(request, page);
        size = toSize(request, size);
        PageInfo all = roleService.findAll(page, size);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(request, response);
    }


    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList", all);
        request.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将数据获取到，封装成一个对象
        Role role = BeanUtil.fillBean(request, Role.class, "yyyy-MM-dd");
        //调用业务层接口save
        roleService.save(role);
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");

    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //查询要修改的数据
        String id = request.getParameter("id");
        Role role = roleService.findById(id);
        //将数据页面加载到指定区域，供页面获取
        request.setAttribute("role", role);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(request, response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将数据获取到，封装成一个对象
        Role role = BeanUtil.fillBean(request, Role.class, "yyyy-MM-dd");
        //调用业务层接口update
        roleService.update(role);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将数据获取到，封装成一个对象
        Role role = BeanUtil.fillBean(request, Role.class);
        //调用业务层接口update
        roleService.delete(role);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void author(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要授权的角色id
        String roleId = request.getParameter("id");
        //使用id查询对应的数据（角色id对应的模块信息）
        Role role = roleService.findById(roleId);
        request.setAttribute("role", role);
        //根据当前的角色id获取所有的模块数据，并加载关系数据
        List<Map> authorDataByRoleId = moduleService.findAuthorDataByRoleId(roleId);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(authorDataByRoleId);

        request.setAttribute("roleModuleJson", json);
        //跳转到树页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(request, response);
    }

    private void updateRoleModule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("roleId");
        String moduleIds = request.getParameter("moduleIds");
        roleService.updateRoleModule(roleId, moduleIds);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


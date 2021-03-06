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
@SuppressWarnings("all")
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

        //???????????????
        //????????????
        int page = 1;
        int size = 5;
        page = toPage(request, page);
        size = toSize(request, size);
        PageInfo all = roleService.findAll(page, size);
        //?????????????????????????????????
        request.setAttribute("page", all);
        //????????????
        request.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(request, response);
    }


    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList", all);
        request.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //??????????????????????????????????????????
        Role role = BeanUtil.fillBean(request, Role.class, "yyyy-MM-dd");
        //?????????????????????save
        roleService.save(role);
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");

    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //????????????????????????
        String id = request.getParameter("id");
        Role role = roleService.findById(id);
        //??????????????????????????????????????????????????????
        request.setAttribute("role", role);
        //????????????
        request.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(request, response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //??????????????????????????????????????????
        Role role = BeanUtil.fillBean(request, Role.class, "yyyy-MM-dd");
        //?????????????????????update
        roleService.update(role);
        //??????????????????list
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //??????????????????????????????????????????
        Role role = BeanUtil.fillBean(request, Role.class);
        //?????????????????????update
        roleService.delete(role);
        //??????????????????list
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void author(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //????????????????????????id
        String roleId = request.getParameter("id");
        //??????id??????????????????????????????id????????????????????????
        Role role = roleService.findById(roleId);
        request.setAttribute("role", role);
        //?????????????????????id???????????????????????????????????????????????????
        List<Map> authorDataByRoleId = moduleService.findAuthorDataByRoleId(roleId);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(authorDataByRoleId);

        request.setAttribute("roleModuleJson", json);
        //??????????????????
        request.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(request, response);
    }

    private void updateRoleModule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("roleId");
        String moduleIds = request.getParameter("moduleIds");
        roleService.updateRoleModule(roleId, moduleIds);
        //??????????????????list
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


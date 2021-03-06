package com.huahua.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.system.*;
import com.huahua.domain.system.Module;
import com.huahua.utils.BeanUtil;
import com.huahua.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.List;


/**
 * @author Huahua
 */
@SuppressWarnings("all")
@WebServlet("/system/user")
@Component
public class UserServlet extends BaseServlet {


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
        } else if ("userRoleList".equals(operation)) {
            try {
                this.userRoleList(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("updateRole".equals(operation)) {
            try {
                this.updateRole(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("login".equals(operation)) {
            try {
                this.login(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("logout".equals(operation)) {
            try {
                this.logout(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("home".equals(operation)) {
            try {
                this.home(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {

        //???????????????
        //????????????
        int page = 1;
        int size = 5;
        page = toPage(request, page);
        size = toSize(request, size);
        PageInfo all = userService.findAll(page, size);
        //?????????????????????????????????
        request.setAttribute("page", all);
        //????????????
        request.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(request, response);
    }


    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList", all);
        request.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        if (StringUtils.isNotBlank(request.getParameter("birthday"))
                && StringUtils.isNotBlank(request.getParameter("joinDate"))
                && StringUtils.isNotBlank(request.getParameter("deptId"))) {
            //??????????????????????????????????????????
            User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
            //?????????????????????save
            userService.save(user);
            //??????????????????list
            response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
        } else {
            response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
        }
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList", all);
        //????????????????????????
        String id = request.getParameter("id");
        User user = userService.findById(id);
        //??????????????????????????????????????????????????????
        request.setAttribute("user", user);
        //????????????
        request.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(request, response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //??????????????????????????????????????????
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        //?????????????????????update
        userService.update(user);
        //??????????????????list

        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //??????????????????????????????????????????
        User user = BeanUtil.fillBean(request, User.class);
        //?????????????????????update
        userService.delete(user);
        //??????????????????list
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void userRoleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        User user = userService.findById(userId);
        //??????????????????????????????????????????????????????
        request.setAttribute("user", user);
        List<Role> all = roleService.findAllRoleByUserId(userId);
        request.setAttribute("roleList", all);
        request.getRequestDispatcher("/WEB-INF/pages/system/user/role.jsp").forward(request, response);
    }

    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String[] roleIds = request.getParameterValues("roleIds");
        userService.updateRole(userId, roleIds);
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

        User user = userService.login(email, pwd);
        SysLog sysLog = new SysLog();
        if (user != null) {
            String ip4 = Inet4Address.getLocalHost().getHostAddress();
            sysLog.setUserId(user.getId());
            sysLog.setIp(ip4);
            sysLogService.save(sysLog);
            request.getSession().setAttribute("loginUser", user);
            //????????????????????????????????????????????????????????????????????????
            List<Module> moduleList = userService.findMoudeleById(user.getId());
            request.setAttribute("moduleList", moduleList);

            //???????????????????????????????????????????????????url???????????????????????????
            StringBuffer sbf = new StringBuffer();
            for (Module m : moduleList) {
                sbf.append(m.getCurl());
                sbf.append(',');
            }
            request.getSession().setAttribute("authorStr",sbf.toString());
            request.getSession().removeAttribute("error");
            //??????????????????
            request.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(request, response);
        } else {
//            request.setAttribute("msg", "????????????????????????????????????????????????");
            request.getSession().setAttribute("error","????????????????????????????????????????????????");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("loginUser");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


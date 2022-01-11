package com.huahua.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.system.Dept;
import com.huahua.domain.system.Module;
import com.huahua.domain.system.Role;
import com.huahua.domain.system.User;
import com.huahua.utils.BeanUtil;
import com.huahua.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;
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

        //进入列表页
        //获取数据
        int page = 1;
        int size = 5;
        page = toPage(request, page);
        size = toSize(request, size);
        PageInfo all = userService.findAll(page, size);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
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
            //将数据获取到，封装成一个对象
            User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
            //调用业务层接口save
            userService.save(user);
            //跳转回到页面list
            response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
        } else {
            response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
        }
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList", all);
        //查询要修改的数据
        String id = request.getParameter("id");
        User user = userService.findById(id);
        //将数据页面加载到指定区域，供页面获取
        request.setAttribute("user", user);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(request, response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //将数据获取到，封装成一个对象
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        //调用业务层接口update
        userService.update(user);
        //跳转回到页面list

        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //将数据获取到，封装成一个对象
        User user = BeanUtil.fillBean(request, User.class);
        //调用业务层接口update
        userService.delete(user);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void userRoleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        User user = userService.findById(userId);
        //将数据页面加载到指定区域，供页面获取
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
        if (user != null) {
            request.getSession().setAttribute("loginUser", user);
            //如果登录成功，加载该用户对应的角色对应的所有模块
            List<Module> moduleList = userService.findMoudeleById(user.getId());
            request.setAttribute("moduleList", moduleList);

            //当前登录用户对应的可操作模块的所有url拼接成一个大字符串
            StringBuffer sbf = new StringBuffer();
            for (Module m : moduleList) {
                sbf.append(m.getCurl());
                sbf.append(',');
            }
            System.out.println(sbf.toString());
            request.getSession().setAttribute("authorStr",sbf.toString());
            //跳转到主界面
            request.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(request, response);
        } else {
//            request.setAttribute("msg", "密码或用户名输入错误！请重新输入");
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


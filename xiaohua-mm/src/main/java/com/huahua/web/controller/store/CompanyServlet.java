package com.huahua.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.store.Company;
import com.huahua.utils.BeanUtil;
import com.huahua.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//uri:/store/company?operation=list

/**
 * @author Huahua
 */
@SuppressWarnings("all")
@WebServlet("/store/company")
@Component
public class CompanyServlet extends BaseServlet {


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
        //进入列表页
        //获取数据
        int page = 1;
        int size = 5;
        page = toPage(request,page);
        size = toSize(request,size);
        PageInfo all = companyService.findAll(page, size);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(request, response);
    }


    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        if(StringUtils.isNotBlank(request.getParameter("expirationDate"))){
            //将数据获取到，封装成一个对象
            Company company = BeanUtil.fillBean(request, Company.class, "yyyy-MM-dd");
            //调用业务层接口save
            companyService.save(company);
            //跳转回到页面list

        }
        response.sendRedirect(request.getContextPath() + "/store/company?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //查询要修改的数据
        String id = request.getParameter("id");
        Company company = companyService.findById(id);
        //将数据页面加载到指定区域，供页面获取
        request.setAttribute("company", company);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(request, response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //将数据获取到，封装成一个对象
        Company company = BeanUtil.fillBean(request, Company.class, "yyyy-MM-dd");
        //调用业务层接口update
        companyService.update(company);
        //跳转回到页面list

        response.sendRedirect(request.getContextPath() + "/store/company?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //将数据获取到，封装成一个对象
        Company company = BeanUtil.fillBean(request, Company.class);
        //调用业务层接口update
        companyService.delete(company);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/store/company?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package com.huahua.web.controller;

import com.huahua.config.SpringConfig;
import com.huahua.service.front.MemberService;
import com.huahua.service.store.*;
import com.huahua.service.system.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Huahua
 */
@Component
@SuppressWarnings("all")
public class BaseServlet extends HttpServlet {


    protected CompanyService companyService;
    protected DeptService deptService;
    protected UserService userService;
    protected CourseService courseService;
    protected CatalogService catalogService;
    protected QuestionService questionService;
    protected QuestionItemService questionItemService;
    protected RoleService roleService;
    protected ModuleService moduleService;
    protected MemberService memberService;
    protected ExamineLogService examineLogService;
    protected SysLogService sysLogService;


    protected final String list = "list";
    protected final String toAdd = "toAdd";
    protected final String save = "save";
    protected final String toEdit = "toEdit";
    protected final String edit = "edit";
    protected final String delete = "delete";

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        companyService = (CompanyService) ctx.getBean("companyService");
        deptService = (DeptService) ctx.getBean("deptService");
        userService = (UserService) ctx.getBean("userService");
        courseService = (CourseService) ctx.getBean("courseService");
        catalogService = (CatalogService) ctx.getBean("catalogService");
        questionService = (QuestionService) ctx.getBean("questionService");
        questionItemService = (QuestionItemService) ctx.getBean("questionItemService");
        roleService = (RoleService) ctx.getBean("roleService");
        moduleService = (ModuleService) ctx.getBean("moduleService");
        memberService = (MemberService) ctx.getBean("memberService");
        examineLogService = (ExamineLogService) ctx.getBean("examineLogService");
        sysLogService = (SysLogService) ctx.getBean("sysLogService");
    }



    protected int toPage(HttpServletRequest request,int page){
        String page1 = "page";
        if (StringUtils.isNotBlank(request.getParameter(page1))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        return page;
    }

    protected int toSize(HttpServletRequest request,int size) {
        String size1 = "size";
        if (StringUtils.isNotBlank(request.getParameter(size1))) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        return size;
    }

}

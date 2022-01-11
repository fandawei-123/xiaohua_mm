package com.huahua.web.controller;

import com.alibaba.fastjson.JSON;
import com.huahua.config.SpringConfig;
import com.huahua.service.front.ExamService;
import com.huahua.service.front.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Huahua
 */
@Component("baseServlet")
public class BaseServlet extends HttpServlet {

    protected MemberService memberService;
    protected ExamService examService;

    @Override
    public void init() throws ServletException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        memberService = (MemberService) ctx.getBean("memberService");
        examService = (ExamService) ctx.getBean("examService");
    }

    protected  <T> T getData(HttpServletRequest request, Class<T> clazz) throws IOException {
        String json = JSON.parseObject(request.getInputStream(), String.class);
        return JSON.parseObject(json, clazz);
    }

    protected void returnData(HttpServletResponse response,Result result) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        JSON.writeJSONString(response.getOutputStream(), result);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        int lastIndexOf = url.lastIndexOf('/');
        String methodName = url.substring(lastIndexOf +1);

        Class<? extends BaseServlet> clazz = this.getClass();
        try {
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            Result result = (Result) method.invoke(this, request, response);
            returnData(response,result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

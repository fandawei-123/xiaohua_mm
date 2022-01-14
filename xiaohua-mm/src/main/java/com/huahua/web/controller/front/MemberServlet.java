package com.huahua.web.controller.front;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.front.Member;
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
@SuppressWarnings("all")
@WebServlet("/front/member")
@Component
public class MemberServlet extends BaseServlet {


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
        } else if ("toCheck".equals(operation)) {
            try {
                this.toCheck(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("updateState".equals(operation)) {
            try {
                this.updateState(request, response);
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
        PageInfo all = memberService.findAll(page, size);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/front/member/list.jsp").forward(request, response);
    }


    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //将数据获取到，封装成一个对象
        Member member = BeanUtil.fillBean(request, Member.class);
        //调用业务层接口update
        memberService.delete(member);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/front/member?operation=list");
    }

    private void toCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Member member = memberService.findById(id);
        request.setAttribute("member", member);
        request.getRequestDispatcher("/WEB-INF/pages/front/member/information.jsp").forward(request, response);
    }


    private void updateState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String state = request.getParameter("state");
        String memberId = request.getParameter("id");
        memberService.updateState(memberId, state);
        response.sendRedirect(request.getContextPath() + "/front/member?operation=toCheck&id=" + memberId);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


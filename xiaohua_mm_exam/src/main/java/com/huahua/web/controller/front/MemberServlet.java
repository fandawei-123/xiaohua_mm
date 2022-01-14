package com.huahua.web.controller.front;

import com.huahua.domain.front.Member;
import com.huahua.web.controller.BaseServlet;
import com.huahua.web.controller.Code;
import com.huahua.web.controller.Result;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Huahua
 */
@Component
@WebServlet("/member/*")
public class MemberServlet extends BaseServlet {

    public Result register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request, Member.class);
        boolean flag = memberService.register(member);
        return new Result("注册成功！", flag);
    }

    public Result login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request, Member.class);
        member = memberService.login(member.getEmail(), member.getPassword());
        if (member != null && "1".equals(member.getState())) {
            return new Result("登录成功！", member);
        } else {
            if(member == null) {
                return new Result("用户名或密码错误,请检查后重试！", false, null, Code.LOGIN_FAIL);
            }else{
                return new Result("您的账号已被禁用，请联系管理员！", false, null, Code.LOGIN_FAIL);
            }
        }
    }

    public Result checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request, Member.class);
        String nickName = memberService.getLoginInfo(member.getId());
        return new Result("", nickName);
    }

    public Result logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request, Member.class);
        boolean flag = memberService.logout(member.getId());
        if (flag) {
            return new Result("退出成功！", member);
        } else {
            return new Result("用户名或密码错误,请检查后重试", false, flag, Code.LOGOUT_FAIL);
        }
    }

}

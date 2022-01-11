package com.huahua.web.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huahua.domain.front.ExamQuestion;
import com.huahua.domain.store.Question;
import com.huahua.web.controller.BaseServlet;
import com.huahua.web.controller.Result;
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
@Component
@WebServlet("/exam/*")
public class ExamServlet extends BaseServlet {

    public Result getPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Question> questionList = examService.getPaper();
        return new Result("试卷生成成功！", questionList);
    }

    public Result applyPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //memberId:?????,results:[{},{}]
        //1.得到全部请求的json数据
        String json = JSONObject.parseObject(request.getInputStream(), String.class);
        //2.将json数据转换为json对象
        JSONObject jsonObject = JSON.parseObject(json);
        //3.获取当前提交试卷人的id
        String memberId = jsonObject.getObject("memberId", String.class);
        //4.获取当前提交的试卷信息
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        List<ExamQuestion> examQuestionList = jsonArray.toJavaList(ExamQuestion.class);

        boolean flag = examService.applyPaper(memberId, examQuestionList);

        return new Result("试卷提交成功！", flag);
    }
}

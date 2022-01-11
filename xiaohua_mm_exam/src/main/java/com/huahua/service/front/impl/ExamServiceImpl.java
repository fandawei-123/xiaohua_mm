package com.huahua.service.front.impl;

import com.huahua.dao.front.ExamPaperDao;
import com.huahua.dao.front.ExamQuestionDao;
import com.huahua.domain.front.ExamPaper;
import com.huahua.domain.front.ExamQuestion;
import com.huahua.domain.store.Question;
import com.huahua.dao.store.QuestionDao;
import com.huahua.service.front.ExamService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@Component("examService")
public class ExamServiceImpl implements ExamService {
    @Resource
    private QuestionDao questionDao;
    @Resource
    private ExamPaperDao examPaperDao;
    @Resource
    private ExamQuestionDao examQuestionDao;

    @Override
    public List<Question> getPaper() {
        return questionDao.findAll();
    }

    @Override
    public boolean applyPaper(String memberId, List<ExamQuestion> examQuestionList) {
        boolean flag = true;
        //.提交保存的试卷信息
        ExamPaper examPaper  = new ExamPaper();
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("[a-zA-z]","");
        uuid = uuid.replaceAll("-","");

        examPaper.setId("EP"+ uuid);
        examPaper.setApplyTime(new Date());
        examPaper.setMemberId(memberId);
        examPaper.setState("1");
        flag = flag && examPaperDao.save(examPaper) > 0;
        //4.提交保存的试卷中的所有题目对应的答案信息
        int i = 0;
        for(ExamQuestion eq: examQuestionList) {
            eq.setId("EQ"+ uuid.subSequence(0,12) + i++);
            eq.setExamPaperId("EP"+ uuid);
            flag = flag && examQuestionDao.save(eq) > 0;
        }
        return flag;
    }
}

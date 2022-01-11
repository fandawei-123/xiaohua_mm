package com.huahua.service.front;

import com.huahua.domain.front.ExamQuestion;
import com.huahua.domain.store.Question;

import java.util.List;

/**
 * @author Huahua
 */
public interface ExamService {
    /**
     *
     * @return
     */
    List<Question> getPaper();

    /**
     *
     * @param memberId
     * @param examQuestionList
     * @return
     */
    boolean applyPaper(String memberId, List<ExamQuestion> examQuestionList);
}

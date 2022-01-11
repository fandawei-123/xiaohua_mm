package com.huahua.dao.front;

import com.huahua.domain.front.ExamQuestion;
import org.apache.ibatis.annotations.Insert;

import javax.annotation.Resource;

/**
 * @author Huahua
 */
@Resource
public interface ExamQuestionDao {

    @Insert("insert into tr_member_question (id, question_id, examinationpaper_id, answer_result)\n" +
            "values (#{id}, #{questionId}, #{examPaperId}, #{answer})")
    int save(ExamQuestion eq);
}

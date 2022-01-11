package com.huahua.dao.store;

import com.huahua.domain.store.Question;
import com.huahua.domain.store.QuestionItem;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Huahua
 */
@Resource
public interface QuestionItemDao {


    @Select("select\n" +
            "id, question_id, content, is_right\n" +
            "from st_question_item\n" +
            "where question_id = #{questionId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "isRight", column = "is_right"),
    })
    List<QuestionItem> findByQuestionId(String questionId);
}

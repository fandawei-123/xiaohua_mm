package com.huahua.dao.store;

import com.huahua.domain.store.QuestionItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface QuestionItemDao {


    @Insert("insert into st_question_item (id, question_id, content,\n" +
            "picture, is_right)\n" +
            "values (#{id}, #{questionId}, #{content},\n" +
            "#{picture}, #{isRight})")
    void save(QuestionItem questionItem);

    @Delete("delete from st_question_item where id = #{id}")
    void delete(QuestionItem questionItem);

    @Update("update st_question_item\n" +
            "set\n" +
            "content = #{content},\n" +
            "picture = #{picture},\n" +
            "is_right = #{isRight}\n" +
            "where id = #{id}")
    void update(QuestionItem questionItem);

    @Select("select\n" +
            "id, question_id, content, picture, is_right\n" +
            "from st_question_item\n" +
            "where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "isRight", column = "is_right"),
            @Result(property = "picture", column = "picture")
    })
    QuestionItem findById(String id);


    /**
     * 根据题目id查询对应选项
     * @param questionId
     * @return
     */
    @Select("select\n" +
            "id, question_id, content, picture, is_right\n" +
            "from st_question_item\n" +
            "where question_id = #{questionId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "isRight", column = "is_right"),
            @Result(property = "picture", column = "picture")
    })
    List<QuestionItem> findByQuestionId(String questionId);

    @Delete("delete from st_question_item where question_id = #{questionId}")
    void deleteAllItem(String questionId);
}

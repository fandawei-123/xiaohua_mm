package com.huahua.dao.store;


import com.huahua.domain.store.ExamineLog;
import com.huahua.domain.store.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface ExamineLogDao {

    @Insert("insert into st_examine_log \n" +
            "(id,comments,status,question_id,user_id,review_time)\n" +
            "values(#{id},#{comments},#{status},\n" +
            "#{questionId},#{userId},\n" +
            "#{reviewTime})")
    void save(ExamineLog examineLog);

    @Update("update st_examine_log set comments=#{comments},\n" +
            "status=#{status},user_id=#{userId},\n" +
            "review_time=#{reviewTime}\n" +
            "where question_id=#{questionId}")
    void update(ExamineLog examineLog);

    @Delete("delete from st_examine_log where id = #{id}")
    void delete(ExamineLog examineLog);

    @Delete("delete from st_examine_log where question_id = #{questionId}")
    void deleteByQuestionId(String questionId);

    @Select("select * from st_examine_log where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "comments", column = "comments"),
            @Result(property = "status", column = "status"),
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "reviewTime", column = "review_time"),
            @Result(property = "question",
                    javaType = Question.class,
                    column = "question_id",
                    many = @Many(select = "com.huahua.dao.store.QuestionDao.findById")
            )
    })
    ExamineLog findById(String id);


    @Select("select * from st_examine_log")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "comments", column = "comments"),
            @Result(property = "status", column = "status"),
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "reviewTime", column = "review_time"),
            @Result(property = "question",
                    javaType = Question.class,
                    column = "question_id",
                    many = @Many(select = "com.huahua.dao.store.QuestionDao.findById")
            )
    })
    List<ExamineLog> findAll();


    @Select("select * from st_examine_log where question_id=#{questionId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "comments", column = "comments"),
            @Result(property = "status", column = "status"),
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "reviewTime", column = "review_time"),
            @Result(property = "question",
                    javaType = Question.class,
                    column = "question_id",
                    many = @Many(select = "com.huahua.dao.store.QuestionDao.findById")
            )
    })
    ExamineLog findByQuestionId(String questionId);
}

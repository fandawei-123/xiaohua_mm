package com.huahua.dao.store;

import com.huahua.domain.store.Question;
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
public interface QuestionDao {

//
//    @Select("select\n" +
//            "id,subject,type,RAND() as r\n" +
//            "from st_question\n" +
//            "order by r \n" +
//            " limit 0,10")
            @Select("select\n" +
            "id,subject,type\n" +
            "from st_question\n" +
            "where id='661' or id='Q3540398435'")/**/
    @Results({
            @Result(column="id", property="id"),
            @Result(column="subject", property="subject"),
            @Result(column="type", property="type"),
            @Result(
                    property="questionItems",
                    javaType = List.class,
                    column = "id",
                    many = @Many(select="com.huahua.dao.store.QuestionItemDao.findByQuestionId")
            )
    })
    List<Question> findAll();
}

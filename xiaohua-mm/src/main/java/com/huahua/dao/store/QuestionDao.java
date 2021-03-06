package com.huahua.dao.store;


import com.huahua.domain.store.Catalog;
import com.huahua.domain.store.Company;
import com.huahua.domain.store.Question;
import com.huahua.domain.system.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface QuestionDao {


    @Insert("insert into st_question(id, company_id, catalog_id, remark, subject, analysis, type,\n" +
            "difficulty, is_classic, state, review_status, create_time, picture,create_by)\n" +
            "values (#{id}, #{companyId}, #{catalogId},\n" +
            "#{remark}, #{subject}, #{analysis},\n" +
            "#{type}, #{difficulty}, #{isClassic},\n" +
            "#{state}, #{reviewStatus}, #{createTime}, #{picture}, #{createUserId})")
    void save(Question question);

    @Delete("delete from st_question where id = #{id}")
    void delete(Question question);

    @Update("update\n" +
            "st_question\n" +
            "set\n" +
            "company_id = #{companyId},\n" +
            "catalog_id = #{catalogId},\n" +
            "remark = #{remark},\n" +
            "subject = #{subject},\n" +
            "analysis = #{analysis},\n" +
            "difficulty = #{difficulty},\n" +
            "is_classic = #{isClassic},\n" +
            "picture = #{picture},\n" +
            "state = #{state},\n" +
            "type = #{type}\n" +
            "where id = #{id}")
    void update(Question question);

    @Update("update st_question set review_status=#{status} where id=#{questionId}")
    void updateReviewStatus(@Param("status") String status, @Param("questionId") String questionId);

    @Select("select\n" +
            "id, catalog_id, company_id, remark,subject,analysis,type, difficulty, is_classic,\n" +
            "state, review_status, create_time, picture,create_by\n" +
            "from st_question\n" +
            "where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "catalogId", column = "catalog_id"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "subject", column = "subject"),
            @Result(property = "type", column = "type"),
            @Result(property = "difficulty", column = "difficulty"),
            @Result(property = "isClassic", column = "is_classic"),
            @Result(property = "state", column = "state"),
            @Result(property = "reviewStatus", column = "review_status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "company",
                    javaType = Company.class,
                    column = "company_id",
                    many = @Many(select = "com.huahua.dao.store.CompanyDao.findById")
            ),
            @Result(property = "catalog",
                    javaType = Catalog.class,
                    column = "catalog_id",
                    many = @Many(select = "com.huahua.dao.store.CatalogDao.findById")
            ),
            @Result(property = "createUser",
                    javaType = User.class,
                    column = "create_by",
                    many = @Many(select = "com.huahua.dao.system.UserDao.findById")
            )
    })
    Question findById(String id);


    @Select("select\n" +
            "id, catalog_id, company_id, remark,subject,analysis,type, difficulty, is_classic,\n" +
            "state, review_status, create_time, picture,create_by\n" +
            "from st_question\n" +
            "order by create_time desc")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "catalogId", column = "catalog_id"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "subject", column = "subject"),
            @Result(property = "type", column = "type"),
            @Result(property = "difficulty", column = "difficulty"),
            @Result(property = "isClassic", column = "is_classic"),
            @Result(property = "state", column = "state"),
            @Result(property = "reviewStatus", column = "review_status"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "createUserId", column = "create_by"),
            @Result(property = "company",
                    javaType = Company.class,
                    column = "company_id",
                    many = @Many(select = "com.huahua.dao.store.CompanyDao.findById")
            ),
            @Result(property = "catalog",
                    javaType = Catalog.class,
                    column = "catalog_id",
                    many = @Many(select = "com.huahua.dao.store.CatalogDao.findById")
            ),
            @Result(property = "createUser",
                    javaType = User.class,
                    column = "create_by",
                    many = @Many(select = "com.huahua.dao.system.UserDao.findById")
            )
    })
    List<Question> findAll();

}

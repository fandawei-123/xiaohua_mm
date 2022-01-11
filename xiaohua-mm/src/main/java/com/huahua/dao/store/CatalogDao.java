package com.huahua.dao.store;


import com.huahua.domain.store.Catalog;
import com.huahua.domain.store.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface CatalogDao {


    @Insert("insert into st_catalog (id, name, remark , state , create_time , course_id)\n" +
            "values (#{id}, #{name}, #{remark},\n" +
            "#{state} , #{createTime}, #{courseId})")
    void save(Catalog catalog);

    @Delete("delete from st_catalog where id = #{id}")
    void delete(Catalog catalog);

    @Update(" update st_catalog\n" +
            "set name = #{name},\n" +
            "remark = #{remark},\n" +
            "state = #{state},\n" +
            "course_id = #{courseId}\n" +
            "where id = #{id}")
    void update(Catalog catalog);

    @Select("select\n" +
            "id, name, remark , state , create_time , course_id\n" +
            "from st_catalog\n" +
            "where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "state", column = "state"),
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "course",
                    javaType = Course.class,
                    column = "course_id",
                    many = @Many(select = "com.huahua.dao.store.CourseDao.findById")
            )
    })
    Catalog findById(String id);


    @Select("select\n" +
            "id, name, remark , state , create_time , course_id\n" +
            "from st_catalog")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "state", column = "state"),
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "course",
                    javaType = Course.class,
                    column = "course_id",
                    many = @Many(select = "com.huahua.dao.store.CourseDao.findById")
            )
    })
    List<Catalog> findAll();
}

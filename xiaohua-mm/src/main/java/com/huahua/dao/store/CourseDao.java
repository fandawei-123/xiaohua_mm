package com.huahua.dao.store;


import com.huahua.domain.store.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface CourseDao {


    @Insert("insert into st_course (id, name, remark,\n" +
            "state, create_time)\n" +
            "values (#{id}, #{name}, #{remark},\n" +
            "#{state}, #{createTime}\n" +
            ")")
    void save(Course course);

    @Delete("delete from st_course where id = #{id}")
    void delete(Course course);

    @Update("update st_course\n" +
            "set name = #{name},\n" +
            "remark = #{remark},\n" +
            "state = #{state}\n" +
            "where id = #{id}")
    void update(Course course);

    @Select("select\n" +
            "id, name, state, remark, create_time\n" +
            "from st_course\n" +
            "where id = #{id}")
    Course findById(String id);


    @Select("select\n" +
            "id, name, state, remark, create_time\n" +
            "from st_course")
    List<Course> findAll();
}

package com.huahua.dao.system;


import com.huahua.domain.system.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface DeptDao {


    @Insert("insert into ss_dept (dept_id, dept_name, parent_id,state)\n" +
            "values (#{id}, #{deptName}, #{parentId},#{state})")
    void save(Dept dept);

    @Delete("delete from ss_dept where dept_id = #{id}")
    void delete(Dept dept);

    @Update("update ss_dept\n" +
            "set dept_name = #{deptName},\n" +
            "parent_id = #{parentId},\n" +
            "state = #{state}\n" +
            "where dept_id = #{id}")
    void update(Dept dept);

    @Select("select * from ss_dept where dept_id = #{id}")
    @Results({
            @Result(property = "id", column = "dept_id"),
            @Result(property = "deptName", column = "dept_name"),
            @Result(property = "state", column = "state"),
            @Result(property = "parent",
                    javaType = Dept.class,
                    column = "parent_id",
                    many = @Many(select = "com.huahua.dao.system.DeptDao.findById")
            )
    })
    Dept findById(String id);


    @Select("select * from ss_dept")
    @Results({
            @Result(property = "id", column = "dept_id"),
            @Result(property = "deptName", column = "dept_name"),
            @Result(property = "state", column = "state"),
            @Result(property = "parent",
                    javaType = Dept.class,
                    column = "parent_id",
                    many = @Many(select = "com.huahua.dao.system.DeptDao.findById")
            )
    })
    List<Dept> findAll();


}

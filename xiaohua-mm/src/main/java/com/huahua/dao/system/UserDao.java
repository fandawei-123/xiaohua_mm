package com.huahua.dao.system;

import com.huahua.domain.system.Dept;
import com.huahua.domain.system.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface UserDao {


    @Insert("insert into ss_user (user_id, email, user_name,  password, state,\n" +
            "gender, telephone, birthday, join_date, dept_id)\n" +
            "values (#{id}, #{email}, #{userName},\n" +
            "#{password}, #{state}, #{gender},\n" +
            "#{telephone}, #{birthday}, #{joinDate},\n" +
            "#{deptId})")
    void save(User user);

    @Delete("delete from ss_user where user_id = #{id}")
    void delete(User user);

    @Update("update ss_user\n" +
            "set email = #{email},\n" +
            "user_name = #{userName},\n" +
            "password = #{password},\n" +
            "state = #{state},\n" +
            "gender = #{gender},\n" +
            "telephone = #{telephone},\n" +
            "dept_id = #{deptId}\n" +
            "where user_id = #{id}")
    void update(User user);

    @Select("select * from ss_user where user_id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "state", column = "state"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "joinDate", column = "join_date"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "dept",
                    javaType = Dept.class,
                    column = "dept_id",
                    many = @Many(select = "com.huahua.dao.system.DeptDao.findById")
            )
    })
    User findById(String id);


    @Select("SELECT * FROM `ss_user`")
    @Results({
            @Result(id = true, property = "id", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "state", column = "state"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "joinDate", column = "join_date"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "dept",
                    javaType = Dept.class,
                    column = "dept_id",
                    many = @Many(select = "com.huahua.dao.system.DeptDao.findById")
            )
    })
    List<User> findAll();

    @Delete("delete from ss_role_user where user_id = #{userId}")
    void deleteRole(String userId);

    @Insert("insert into ss_role_user (user_id, role_id)\n" +
            "values (#{userId}, #{roleId})")
    void updateRole(@Param("userId") String userId, @Param("roleId") String roleId);

    @Select("select * from ss_user where email = #{email} and password = #{password}")
    @Results({
            @Result(id = true, property = "id", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "state", column = "state"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "joinDate", column = "join_date"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "dept",
                    javaType = Dept.class,
                    column = "dept_id",
                    many = @Many(select = "com.huahua.dao.system.DeptDao.findById")
            )
    })
    User findByEmailAndPwd(@Param("email") String email, @Param("password") String pwd);
}

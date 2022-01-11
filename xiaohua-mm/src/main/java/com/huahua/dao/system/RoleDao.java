package com.huahua.dao.system;


import com.huahua.domain.system.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface RoleDao {


    @Insert("insert into ss_role (role_id, name, remark, create_time)\n" +
            "values (#{id}, #{name}, #{remark}, #{createTime})")
    void save(Role role);

    @Delete("delete from ss_role where role_id = #{id}")
    void delete(Role role);

    @Update("update ss_role\n" +
            "set name = #{name},\n" +
            "remark = #{remark},\n" +
            "create_time = #{createTime}\n" +
            "where role_id = #{id}")
    void update(Role role);

    @Select("select role_id, name, remark, create_time from ss_role where role_id = #{id}")
    @Results({
            @Result(property = "id", column = "role_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createTime", column="create_time")
    })
    Role findById(String id);


    @Select("select role_id, name, remark, create_time from ss_role")
    @Results({
            @Result(property = "id", column = "role_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "remark", column = "remark"),
            @Result(property = "createTime", column="create_time")
    })
    List<Role> findAll();

    @Delete("delete from ss_role_module where role_id = #{roleId}")
    void deleteRoleModule(String roleId);

    @Insert("insert into ss_role_module (role_id, module_id)\n" +
            "values (#{roleId}, #{moduleId})")
    void saveRoleModule(@Param("roleId") String roleId, @Param("moduleId") String moduleId);

    @Select("select role_id as id, name, case\n" +
            "when role_id in (select role_id from ss_role_user\n" +
            "where user_id = #{userId})\n" +
            "then 'checked'\n" +
            "else ''\n" +
            "end\n" +
            "as remark\n" +
            "from ss_role")
    List<Role> findAllRoleByUserId(String userId);
}

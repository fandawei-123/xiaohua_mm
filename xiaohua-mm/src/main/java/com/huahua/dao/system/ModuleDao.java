package com.huahua.dao.system;

import com.huahua.domain.system.Module;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Huahua
 */
@Repository
public interface ModuleDao {

    @Insert("insert into ss_module (module_id, parent_id, name, ctype, state, curl, remark)\n" +
            "values (#{id}, #{parentId},\n" +
            "#{name}, #{ctype}, #{state},\n" +
            "#{curl},  #{remark})")
    int save(Module module);

    @Delete("delete from ss_module where module_id = #{id}")
    int delete(Module module);

    @Update("update ss_module\n" +
            "set parent_id = #{parentId},\n" +
            "name = #{name},\n" +
            "ctype = #{ctype},\n" +
            "state = #{state},\n" +
            "curl = #{curl},\n" +
            "remark = #{remark}\n" +
            "where module_id = #{id}")
    int update(Module module);

    @Select("select\n" +
            "module_id, parent_id, name, ctype, state, curl, remark\n" +
            "from ss_module\n" +
            "where module_id = #{id}")
    @Results({
            @Result(column="module_id", property="id"),
            @Result(column="parent_id", property="parentId"),
            @Result(column="name", property="name"),
            @Result(column="ctype", property="ctype"),
            @Result(column="state", property="state"),
            @Result(column="curl", property="curl"),
            @Result(column="remark", property="remark"),
            @Result(
                    property = "module",
                    javaType = Module.class,
                    column = "parent_id",
                    many = @Many(select = "com.huahua.dao.system.ModuleDao.findById")
            )
    })
    Module findById(String id);

    @Select("select\n" +
            "module_id, parent_id, name, ctype, state, curl, remark\n" +
            "from ss_module\n" +
            "where is_leaf = 1 or is_leaf = 0\n" +
            "ORDER BY module_id\n")
    @Results({
            @Result(column="module_id", property="id"),
            @Result(column="parent_id", property="parentId"),
            @Result(column="name", property="name"),
            @Result(column="ctype", property="ctype"),
            @Result(column="state", property="state"),
            @Result(column="curl", property="curl"),
            @Result(column="remark", property="remark"),
            @Result(
                    property = "module",
                    javaType = Module.class,
                    column = "parent_id",
                    many = @Many(select = "com.huahua.dao.system.ModuleDao.findById")
            )
    })
    List<Module> findAll();
    @Select("select module_id as id, parent_id as pId, name as name, \n" +
            "case\n" +
            "when module_id in (select module_id from ss_role_module where role_id = #{roleId})\n" +
            "then 'true'\n" +
            "else 'false'\n" +
            "end as checked\n" +
            "from ss_module")
    List<Map> findAuthorDataByRoleId(String roleId);

    @Select("select distinct m.module_id, m.parent_id, m.name, m.ctype, m.state, m.curl, m.remark from\n" +
            "ss_module as m, ss_role_module as rm, ss_role_user as ru\n" +
            "where m.module_id = rm.module_id and\n" +
            "rm.role_id = ru.role_id and \n" +
            "ru.user_id = #{id}")
    @Results({
            @Result(column="module_id", property="id"),
            @Result(column="parent_id", property="parentId"),
            @Result(column="name", property="name"),
            @Result(column="ctype", property="ctype"),
            @Result(column="state", property="state"),
            @Result(column="curl", property="curl"),
            @Result(column="remark", property="remark"),
    })
    List<Module> findModuleByUserId(String id);
}

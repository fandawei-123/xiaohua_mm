package com.huahua.dao.system;

import com.huahua.domain.system.SysLog;
import com.huahua.domain.system.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Huahua
 */
@Repository
public interface SysLogDao {

    @Insert("insert into ss_sys_log (id, ip, time, method, action, uid)\n" +
            "values (#{id}, #{ip}, #{time}, #{method}, #{action}, #{userId})")
    void save(SysLog sysLog);

    @Delete("delete from ss_sys_log where id = #{id}")
    void delete(SysLog sysLog);


    @Select("select * from ss_sys_log where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "uid"),
            @Result(property = "ip", column = "ip"),
            @Result(property = "time", column = "time"),
            @Result(property = "action", column = "action"),
            @Result(property = "method", column = "method"),
            @Result(
                    property = "user",
                    javaType = User.class,
                    column = "uid",
                    many = @Many(select = "com.huahua.dao.system.UserDao.findById")
            )
    })
    SysLog findById(String id);


    @Select("select * from ss_sys_log")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "uid"),
            @Result(property = "ip", column = "ip"),
            @Result(property = "time", column = "time"),
            @Result(property = "action", column = "action"),
            @Result(property = "method", column = "method"),
            @Result(
                    property = "user",
                    javaType = User.class,
                    column = "uid",
                    many = @Many(select = "com.huahua.dao.system.UserDao.findById")
            )
    })
    List<SysLog> findAll();


}

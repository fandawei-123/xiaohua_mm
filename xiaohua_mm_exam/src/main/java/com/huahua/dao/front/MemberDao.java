package com.huahua.dao.front;

import com.huahua.domain.front.Member;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;

/**
 * @author Huahua
 */
@Resource
public interface MemberDao {

    @Select("select\n" +
            "id, nick_name,password,gender,birthday,email,telephone,address,register_date,state\n" +
            "from tr_member\n" +
            "where email = #{email} and password = #{password}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "nick_name", property = "nickName"),
            @Result(column = "password", property = "password"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "email", property = "email"),
            @Result(column = "telephone", property = "telephone"),
            @Result(column = "address", property = "address"),
            @Result(column = "register_date", property = "registerDate"),
            @Result(column = "state", property = "state"),
    })
    Member findByEmailAndPwd(@Param("email") String email, @Param("password") String password);

    @Insert("insert into tr_member (id, nick_name, password, gender,\n" +
            "birthday, email,state,telephone, address, register_date)\n" +
            "values (#{id}, #{nickName}, #{password},\n" +
            "#{gender}, #{birthday}, #{email},#{state},\n" +
            "#{telephone}, #{address}, #{registerDate})")
    int save(Member member);


}

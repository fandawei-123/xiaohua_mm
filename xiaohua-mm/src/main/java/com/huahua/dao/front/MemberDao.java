package com.huahua.dao.front;

import com.huahua.domain.front.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface MemberDao {

    @Delete("delete from tr_member where id = #{id}")
    void delete(Member member);

    @Select("select * from tr_member where id = #{id}")
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
    Member findById(String id);


    @Select("select * from tr_member")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "state", column = "state"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "address", column = "address"),
            @Result(property = "registerDate", column = "register_date")
    })
    List<Member> findAll();

    @Update(("update tr_member set state = #{state} where id = #{memberId}"))
    void updateState(@Param("memberId") String memberId, @Param("state") String state);
}

package com.huahua.service.front;


import com.huahua.domain.front.Member;

/**
 * @author Huahua
 */
public interface MemberService {
    /**
     * 注册新用户
     * @param member
     * @return
     */
    boolean register(Member member);

    /**
     *
     * @param email
     * @param password
     * @return
     */
    Member login(String email, String password);

    /**
     * 根据id获取对应登录人昵称
     * @param id
     * @return
     */
    String getLoginInfo(String id);

    /**
     *
     * @param id
     * @return
     */
    boolean logout(String id);
}

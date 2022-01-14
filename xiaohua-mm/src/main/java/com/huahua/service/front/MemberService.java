package com.huahua.service.front;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.front.Member;

import java.util.List;

/**
 * @author Huahua
 */
public interface MemberService {

    /**
     * 删除
     *
     * @param member
     * @return
     */
    void delete(Member member);


    /**
     * 查询单个
     *
     * @param id
     * @return 查询的结果，单个对象
     */
    Member findById(String id);

    /**
     * 查询全部的数据
     *
     * @return 全部数据的列表对象
     */
    List<Member> findAll();

    /**
     * 分页查询数据
     *
     * @param page 页码
     * @param size 每页显示数
     * @return
     */
    PageInfo findAll(int page, int size);


    /**
     * 修改会员启用状态
     * @param memberId
     * @param state
     */
    void updateState(String memberId, String state);
}

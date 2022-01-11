package com.huahua.service.system;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.system.Module;
import com.huahua.domain.system.User;

import java.util.List;

/**
 * @author Huahua
 */
public interface UserService {
    /**
     * 添加
     *
     * @param user
     * @return
     */
    void save(User user);

    /**
     * 删除
     *
     * @param user
     * @return
     */
    void delete(User user);

    /**
     * 修改
     *
     * @param user
     * @return
     */
    void update(User user);


    /**
     * 查询单个
     *
     * @param id
     * @return 查询的结果，单个对象
     */
    User findById(String id);

    /**
     * 查询全部的数据
     *
     * @return 全部数据的列表对象
     */
    List<User> findAll();

    /**
     * 分页查询数据
     *
     * @param page 页码
     * @param size 每页显示数
     * @return
     */
    PageInfo findAll(int page, int size);

    /**
     *
     * @param userId
     * @param roleIds
     */
    void updateRole(String userId, String[] roleIds);

    /**
     * 根据邮箱和密码登录
     * @param email
     * @param pwd
     * @return
     */
    User login(String email, String pwd);

    /**
     * 根据用户id查询用户所有可以操作的模块
     * @param id
     * @return
     */
    List<Module> findMoudeleById(String id);
}

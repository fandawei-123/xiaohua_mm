package com.huahua.service.system;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 * @author Huahua
 */
public interface ModuleService {


    /**
     * 添加
     * @param module
     * @return
     */
    void save(Module module);

    /**
     * 删除
     * @param module
     * @return
     */
    void delete(Module module);

    /**
     * 修改
     * @param module
     * @return
     */
    void update(Module module);


    /**
     * 查询单个
     * @param id
     * @return 查询的结果，单个对象
     */
    Module findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Module> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示数
     * @return
     */
    PageInfo findAll(int page, int size);

    /**
     * 根据劫色id获取对应的所有模块关联数据
     * @param roleId 角色id
     */
    List<Map> findAuthorDataByRoleId(String roleId);
}

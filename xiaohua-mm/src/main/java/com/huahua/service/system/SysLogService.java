package com.huahua.service.system;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.system.SysLog;

import java.util.List;

/**
 * @author Huahua
 */
public interface SysLogService {
    /**
     *
     * @param sysLog
     */
    void save(SysLog sysLog);

    /**
     * 删除
     * @param sysLog
     * @return
     */
    void delete(SysLog sysLog);

    /**
     * 查询单个
     * @param id
     * @return 查询的结果，单个对象
     */
    SysLog findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<SysLog> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示数
     * @return
     */
    PageInfo findAll(int page, int size);
}

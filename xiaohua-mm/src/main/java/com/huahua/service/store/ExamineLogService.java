package com.huahua.service.store;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.store.ExamineLog;

import java.util.List;

/**
 * @author Huahua
 */
public interface ExamineLogService {

    /**
     * 添加
     * @param examineLog
     * @return
     */
    void save(ExamineLog examineLog);

    /**
     * 删除
     * @param examineLog
     * @return
     */
    void delete(ExamineLog examineLog);

    /**
     * 修改
     * @param examineLog
     * @return
     */
    void update(ExamineLog examineLog);


    /**
     * 查询单个
     * @param id
     * @return 查询的结果，单个对象
     */
    ExamineLog findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<ExamineLog> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示数
     * @return
     */
    PageInfo findAll(int page,int size);


    /**
     * 根据题目id返回审核记录
     * @param questionId
     * @return
     */
    ExamineLog findByQuestionId(String questionId);
}

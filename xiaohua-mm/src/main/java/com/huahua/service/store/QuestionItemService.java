package com.huahua.service.store;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.store.QuestionItem;


/**
 * @author Huahua
 */
public interface QuestionItemService {

    /**
     * 添加
     *
     * @param questionItem
     * @return 图片名称
     */
    String save(QuestionItem questionItem,boolean flag);

    /**
     * 删除
     *
     * @param questionItem
     * @return
     */
    void delete(QuestionItem questionItem);

    /**
     * 修改
     *
     * @param questionItem
     * @return
     */
    void update(QuestionItem questionItem,boolean flag);


    /**
     * 查询单个
     *
     * @param id
     * @return 查询的结果，单个对象
     */
    QuestionItem findById(String id);



    /**
     * 分页查询数据
     *
     * @param questionId 题目id
     * @param page 页码
     * @param size 每页显示数
     * @return
     */
    PageInfo findAll(String questionId, int page, int size);

    /**
     * 当删除所对应的题目时，删除所有选项
     * @param questionId
     * @return
     */
//    void deleteAllItem(String questionId);
}

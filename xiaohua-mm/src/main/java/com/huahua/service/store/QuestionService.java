package com.huahua.service.store;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.store.Question;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @author Huahua
 */
public interface QuestionService {

    /**
     * 添加
     * @param question
     * @param flag 判断是否有图片上传
     * @return 图片名称
     */
    String save(Question question,boolean flag);

    /**
     * 删除
     * @param question
     * @return
     */
    @Transactional
    void delete(Question question);

    /**
     * 修改
     * @param question
     * @param flag 判断是否有图片上传
     * @return
     */
    void update(Question question,boolean flag);


    /**
     * 查询单个
     * @param id
     * @return 查询的结果，单个对象
     */
    Question findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Question> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示数
     * @return
     */
    PageInfo findAll(int page,int size);

    /**
     * 获取包含了数据的流对象
     * @return
     */
    ByteArrayOutputStream getReport();
}

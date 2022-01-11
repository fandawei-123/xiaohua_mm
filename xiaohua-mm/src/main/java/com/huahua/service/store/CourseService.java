package com.huahua.service.store;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.store.Course;

import java.util.List;

/**
 * @author Huahua
 */
public interface CourseService {

    /**
     * 添加
     * @param course
     * @return
     */
    void save(Course course);

    /**
     * 删除
     * @param course
     * @return
     */
    void delete(Course course);

    /**
     * 修改
     * @param course
     * @return
     */
    void update(Course course);


    /**
     * 查询单个
     * @param id
     * @return 查询的结果，单个对象
     */
    Course findById(String id);

    /**
     * 查询全部的数据
     * @return 全部数据的列表对象
     */
    List<Course> findAll();

    /**
     * 分页查询数据
     * @param page 页码
     * @param size 每页显示数
     * @return
     */
    PageInfo findAll(int page,int size);


}

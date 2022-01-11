package com.huahua.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.store.CourseDao;
import com.huahua.domain.store.Course;
import com.huahua.service.store.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseDao courseDao;

    @Override
    public void save(Course course) {

        try {
            //id使用UUID的生成策略来获取
            String uuid = UUID.randomUUID().toString();
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < uuid.length() && id.length() < 3; i++) {
                char ch = uuid.charAt(i);
                if (ch > 47 && ch < 58) {
                    id.append(ch);
                }
            }
            course.setId(id.toString());
            //添加时间
            course.setCreateTime(new Date());

            //2.调用Dao层操作
            courseDao.save(course);
            //3.提交事务
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void delete(Course course) {

        try {

            //调用Dao层操作
            courseDao.delete(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void update(Course course) {
        try {

            //调用Dao层操作
            courseDao.update(course);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public Course findById(String id) {
        try {

            //3.调用Dao层操作
            return courseDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public List<Course> findAll() {
        try {
            return courseDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        try {


            //3.调用Dao层操作
            PageHelper.startPage(page, size);
            List<Course> all = courseDao.findAll();
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }
}

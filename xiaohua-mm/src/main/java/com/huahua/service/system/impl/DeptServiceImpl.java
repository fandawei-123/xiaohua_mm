package com.huahua.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.system.DeptDao;
import com.huahua.domain.system.Dept;
import com.huahua.service.system.DeptService;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@SuppressWarnings("all")
@Component("deptService")
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptDao deptDao;

    @Override
    public void save(Dept dept) {

        try {
            //id使用UUID的生成策略来获取
            String uuid = UUID.randomUUID().toString();
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < uuid.length() && id.length() < 10; i++) {
                char ch = uuid.charAt(i);
                if (ch > 47 && ch < 58) {
                    id.append(ch);
                }
            }
            dept.setId(id.toString());
            //2.调用Dao层操作
            deptDao.save(dept);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void delete(Dept dept) {
        try {
            //3.调用Dao层操作
            deptDao.delete(dept);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void update(Dept dept) {
        try {
            //3.调用Dao层操作
            deptDao.update(dept);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public Dept findById(String id) {
        try {
            //3.调用Dao层操作
            return deptDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public List<Dept> findAll() {
        try {
            //3.调用Dao层操作
            return deptDao.findAll();
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
            List<Dept> all = deptDao.findAll();
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }
}

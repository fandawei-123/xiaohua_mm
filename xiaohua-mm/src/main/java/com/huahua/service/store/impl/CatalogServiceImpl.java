package com.huahua.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.store.CatalogDao;
import com.huahua.domain.store.Catalog;
import com.huahua.service.store.CatalogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    @Resource
    private CatalogDao catalogDao;

    @Override
    public void save(Catalog catalog) {

        try {
            //id使用UUID的生成策略来获取
            String uuid = UUID.randomUUID().toString();
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < uuid.length() && id.length() < 5; i++) {
                char ch = uuid.charAt(i);
                if (ch > 47 && ch < 58) {
                    id.append(ch);
                }
            }
            catalog.setId("C" + id);
            //添加时间
            catalog.setCreateTime(new Date());

            //2.调用Dao层操作
            catalogDao.save(catalog);
            //3.提交事务
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void delete(Catalog catalog) {

        try {

            //调用Dao层操作
            catalogDao.delete(catalog);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void update(Catalog catalog) {
        try {

            //调用Dao层操作
            catalogDao.update(catalog);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public Catalog findById(String id) {
        try {

            //3.调用Dao层操作
            return catalogDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public List<Catalog> findAll() {
        try {
            return catalogDao.findAll();
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
            List<Catalog> all = catalogDao.findAll();
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }
}

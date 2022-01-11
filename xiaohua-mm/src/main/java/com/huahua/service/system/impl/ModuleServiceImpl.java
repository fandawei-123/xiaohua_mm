package com.huahua.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.domain.system.Module;
import com.huahua.dao.system.ModuleDao;
import com.huahua.service.system.ModuleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Huahua
 */
@SuppressWarnings("all")
@Component("moduleService")
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleDao moduleDao;

    @Override
    public void save(Module module) {

        try {
            String parentId = module.getParentId();
            //id使用UUID的生成策略来获取
            String uuid = UUID.randomUUID().toString();
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < uuid.length() && id.length() < 10; i++) {
                char ch = uuid.charAt(i);
                if (ch > 47 && ch < 58) {
                    id.append(ch);
                }
            }
            switch (parentId){
                case "1" : module.setId("1" + id.toString());
                break;
                case "2" : module.setId("2" + id.toString());
                break;
                case "3" : module.setId("3" + id.toString());
                break;
                default:
                    module.setId(parentId + id.toString());
            }

            //2.调用Dao层操作
            moduleDao.save(module);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void delete(Module module) {
        try {
            //3.调用Dao层操作
            moduleDao.delete(module);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void update(Module module) {
        try {
            //3.调用Dao层操作
            moduleDao.update(module);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public Module findById(String id) {
        try {
            //3.调用Dao层操作
            return moduleDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public List<Module> findAll() {
        try {
            //3.调用Dao层操作
            return moduleDao.findAll();
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
            List<Module> all = moduleDao.findAll();
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public List<Map> findAuthorDataByRoleId(String roleId) {
        return moduleDao.findAuthorDataByRoleId(roleId);
    }
}

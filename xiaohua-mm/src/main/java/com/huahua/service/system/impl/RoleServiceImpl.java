package com.huahua.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.system.RoleDao;
import com.huahua.domain.system.Role;
import com.huahua.service.system.RoleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@SuppressWarnings("all")
@Component("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public void save(Role role) {

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
            role.setId(id.toString());
            //2.调用Dao层操作
            roleDao.save(role);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void delete(Role role) {
        try {
            //3.调用Dao层操作
            roleDao.delete(role);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void update(Role role) {
        try {
            //3.调用Dao层操作
            roleDao.update(role);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public Role findById(String id) {
        try {
            //3.调用Dao层操作
            return roleDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public List<Role> findAll() {
        try {
            //3.调用Dao层操作
            return roleDao.findAll();
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
            List<Role> all = roleDao.findAll();
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void updateRoleModule(String roleId, String moduleIds) {
        //现有关系全部取消掉
        roleDao.deleteRoleModule(roleId);
        //建立新的关系（多个）
        String[] moduleArray = moduleIds.split(",");
        for (String moduleId : moduleArray) {
            roleDao.saveRoleModule(roleId, moduleId);
        }
    }

    @Override
    public List<Role> findAllRoleByUserId(String userId) {
        return roleDao.findAllRoleByUserId(userId);
    }
}

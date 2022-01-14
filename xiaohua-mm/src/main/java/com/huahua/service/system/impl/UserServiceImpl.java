package com.huahua.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.system.ModuleDao;
import com.huahua.dao.system.UserDao;
import com.huahua.domain.system.Module;
import com.huahua.domain.system.User;
import com.huahua.service.system.UserService;
import com.huahua.utils.MD5Util;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@SuppressWarnings("all")
@Component("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private ModuleDao moduleDao;

    @Override
    public void save(User user) {
        try {
            //id使用UUID的生成策略来获取
            String uuid = UUID.randomUUID().toString();
            StringBuilder id = new StringBuilder();
            int idLength = 10;
            for (int i = 0; i < uuid.length() && id.length() < idLength; i++) {
                char ch = uuid.charAt(i);
                if (ch > 47 && ch < 58) {
                    id.append(ch);
                }
            }
            user.setId("HUA" + id);
            //密码必须经过加密处理
            user.setPassword(MD5Util.code(user.getPassword()));
            //2.调用Dao层操作
            userDao.save(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void delete(User user) {
        try {
            //3.调用Dao层操作
            userDao.delete(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void update(User user) {
        try {
            //3.调用Dao层操作
            user.setPassword(MD5Util.code(user.getPassword()));
            userDao.update(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public User findById(String id) {
        try {
            //3.调用Dao层操作
            return userDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public List<User> findAll() {
        try {
            //3.调用Dao层操作
            return userDao.findAll();
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
            List<User> all = userDao.findAll();
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void updateRole(String userId, String[] roleIds) {
        userDao.deleteRole(userId);
        for (String roleId : roleIds) {
            userDao.updateRole(userId, roleId);
        }
    }

    @Override
    public User login(String email, String pwd) {
        pwd = MD5Util.code(pwd);

        return userDao.findByEmailAndPwd(email, pwd);
    }

    @Override
    public List<Module> findMoudeleById(String id) {
        return moduleDao.findModuleByUserId(id);
    }
}

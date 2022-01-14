package com.huahua.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.system.SysLogDao;
import com.huahua.domain.system.Dept;
import com.huahua.domain.system.SysLog;
import com.huahua.service.system.SysLogService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@SuppressWarnings("all")
@Component("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        try {
            sysLog.setTime(new Date());

            //id使用UUID的生成策略来获取
            String uuid = UUID.randomUUID().toString();
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < uuid.length() && id.length() < 15; i++) {
                char ch = uuid.charAt(i);
                if (ch > 47 && ch < 58) {
                    id.append(ch);
                }
            }
            sysLog.setId(id.toString());
            sysLogDao.save(sysLog);
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void delete(SysLog sysLog) {
        sysLogDao.delete(sysLog);
    }

    @Override
    public SysLog findById(String id) {
        return sysLogDao.findById(id);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }

    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<SysLog> all = sysLogDao.findAll();
        return new PageInfo(all);
    }

}

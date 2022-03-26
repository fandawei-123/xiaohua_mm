package com.huahua.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.store.ExamineLogDao;
import com.huahua.dao.store.QuestionDao;
import com.huahua.dao.system.DeptDao;
import com.huahua.dao.system.UserDao;
import com.huahua.domain.store.ExamineLog;
import com.huahua.domain.store.Question;
import com.huahua.service.store.ExamineLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@Service("examineLogService")
public class ExamineLogServiceImpl implements ExamineLogService {

    @Resource
    private ExamineLogDao examineLogDao;
    @Resource
    private QuestionDao questionDao;
    @Resource
    private UserDao userDao;


    @Override
    public void save(ExamineLog examineLog) {

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
            examineLog.setId("C" + id);
            //添加时间
            examineLog.setReviewTime(new Date());

            examineLogDao.save(examineLog);

            questionDao.updateReviewStatus(examineLog.getStatus(), examineLog.getQuestionId());
            //3.提交事务
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void delete(ExamineLog examineLog) {
        try {
            //调用Dao层操作
            examineLogDao.delete(examineLog);

        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }


    @Override
    public void update(ExamineLog examineLog) {

        examineLogDao.update(examineLog);
        questionDao.updateReviewStatus(examineLog.getStatus(), examineLog.getQuestionId());
    }

    @Override
    public ExamineLog findById(String id) {
        try {
            //3.调用Dao层操作
            ExamineLog examineLog = examineLogDao.findById(id);
            examineLog.setUserId(userDao.findById(examineLog.getUserId()).getUserName());
            return examineLog;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public List<ExamineLog> findAll() {
        try {
            return examineLogDao.findAll();
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
            List<ExamineLog> all = examineLogDao.findAll();
            for (ExamineLog examineLog : all) {
                examineLog.setUserId(userDao.findById(examineLog.getUserId()).getUserName());
            }
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public ExamineLog findByQuestionId(String questionId) {
        return examineLogDao.findByQuestionId(questionId);
    }
}

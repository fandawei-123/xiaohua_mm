package com.huahua.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.store.QuestionItemDao;
import com.huahua.domain.store.QuestionItem;
import com.huahua.service.store.QuestionItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@Service("questionItemService")
public class QuestionItemServiceImpl implements QuestionItemService {

    @Resource
    private QuestionItemDao questionItemDao;

    @Override
    public String save(QuestionItem questionItem,boolean flag) {

        try {
            //id使用UUID的生成策略来获取
            String uuid = UUID.randomUUID().toString();
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < uuid.length() && id.length() < 12; i++) {
                char ch = uuid.charAt(i);
                if (ch > 47 && ch < 58) {
                    id.append(ch);
                }
            }
            questionItem.setId("C" + id);
            if(flag) {
                //设置当前存储图片的名称为该题的id
                questionItem.setPicture("C" + id);
            }
            //2.调用Dao层操作
            questionItemDao.save(questionItem);
            return questionItem.getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void delete(QuestionItem questionItem) {

        try {

            //调用Dao层操作
            questionItemDao.delete(questionItem);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void update(QuestionItem questionItem,boolean flag) {
        try {

            //检测到前端上传文件了，记录文件名，否则不记录
            if(flag) {
                //设置当前存储图片的名称为该题的id
                questionItem.setPicture(questionItem.getId());
            }
            //调用Dao层操作
            questionItemDao.update(questionItem);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public QuestionItem findById(String id) {
        try {

            //3.调用Dao层操作
            return questionItemDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }


    @Override
    public PageInfo findAll(String questionId, int page, int size) {
        try {
            //3.调用Dao层操作
            PageHelper.startPage(page, size);
            List<QuestionItem> all = questionItemDao.findByQuestionId(questionId);
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

//    @Override
//    public void deleteAllItem(String questionId) {
//        try {
//
//            //3.调用Dao层操作
//            questionItemDao.deleteAllItem(questionId);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//            //记录日志
//        }
//    }
}

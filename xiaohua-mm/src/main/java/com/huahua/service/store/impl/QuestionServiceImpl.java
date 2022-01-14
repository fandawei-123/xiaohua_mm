package com.huahua.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huahua.dao.store.ExamineLogDao;
import com.huahua.dao.store.QuestionDao;
import com.huahua.domain.store.ExamineLog;
import com.huahua.domain.store.Question;
import com.huahua.service.store.QuestionService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Huahua
 */
@Service("questionService")
@SuppressWarnings("all")
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionDao questionDao;
    @Resource
    private ExamineLogDao examineLogDao;

    @Override
    public String save(Question question, boolean flag) {

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
            question.setId("Q" + id);
            //审核状态
            question.setReviewStatus("0");
            //添加时间
            question.setCreateTime(new Date());
            //检测到前端上传文件了，记录文件名，否则不记录
            if (flag) {
                //设置当前存储图片的名称为该题的id
                question.setPicture("Q" + id);
            }
            //2.调用Dao层操作
            questionDao.save(question);
            //3.提交事务
            return question.getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void delete(Question question) {

        try {
            examineLogDao.deleteByQuestionId(question.getId());
            //调用Dao层操作
            questionDao.delete(question);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public void update(Question question, boolean flag) {
        try {

            //检测到前端上传文件了，记录文件名，否则不记录
            if (flag) {
                //设置当前存储图片的名称为该题的id
                question.setPicture(question.getId());
            }
            //调用Dao层操作
            questionDao.update(question);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public Question findById(String id) {
        try {

            //3.调用Dao层操作
            return questionDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public List<Question> findAll() {
        try {
            return questionDao.findAll();
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
            List<Question> all = questionDao.findAll();
            return new PageInfo(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        }
    }

    @Override
    public ByteArrayOutputStream getReport() {
        //获取对应数据
        List<Question> questionList = questionDao.findAll();

        //获取到对应的Excel文件，工作簿文件
        Workbook wb = new XSSFWorkbook();
        //创建工作表
        Sheet sheet = wb.createSheet("题目数据文件");

        //设置通用配置
        CellStyle cs_field = wb.createCellStyle();
        cs_field.setAlignment(HorizontalAlignment.CENTER);
        cs_field.setBorderBottom(BorderStyle.THIN);
        cs_field.setBorderLeft(BorderStyle.THIN);
        cs_field.setBorderRight(BorderStyle.THIN);
        cs_field.setBorderTop(BorderStyle.THIN);
//        for (int i = 1; i < 12; i++) {
//            sheet.setColumnWidth();
//        }

        //制作标题
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 12));

        Row row_1 = sheet.createRow(1);
        Cell cell_1_1 = row_1.createCell(1);
        cell_1_1.setCellValue("在线试题导出信息");
        CellStyle cs_title = wb.createCellStyle();


        cs_title.setAlignment(HorizontalAlignment.CENTER);
        cs_title.setVerticalAlignment(VerticalAlignment.CENTER);
        cs_title.setBorderLeft(BorderStyle.THIN);
        cell_1_1.setCellStyle(cs_title);

        CellStyle title_top = wb.createCellStyle();
        CellStyle title_right = wb.createCellStyle();
        title_top.setBorderBottom(BorderStyle.THIN);
        title_right.setBorderLeft(BorderStyle.THIN);
        Row row_0 = sheet.createRow(0);
        for (int i = 1; i < 13; i++) {
            Cell cell_0_temp = row_0.createCell(i);
            cell_0_temp.setCellStyle(title_top);
        }
        Cell cell_1_13 = row_1.createCell(13);
        cell_1_13.setCellStyle(title_right);


        //制作表头
        String[] fields = {"题目ID", "所属公司ID", "所属目录ID", "题目简介", "题干描述",
                "题干配图", "题目分析", "题目类型", "题目难度", "是否经典题", "题目状态", "审核状态"};
        Row row_2 = sheet.createRow(2);
        for (int i = 0; i < fields.length; i++) {
            Cell cell_2_temp = row_2.createCell(1 + i);
            cell_2_temp.setCellValue(fields[i]);

            cell_2_temp.setCellStyle(cs_field);
        }


        //制作数据区


        int row_index = 3;

        for (Question q : questionList) {
            int cell_index = 0;
            Row row_temp = sheet.createRow(row_index++);
            Cell cell_data_1 = row_temp.createCell(1 + cell_index++);
            cell_data_1.setCellValue(q.getId());
            cell_data_1.setCellStyle(cs_field);

            Cell cell_data_2 = row_temp.createCell(1 + cell_index++);
            cell_data_2.setCellValue(q.getCompanyId());
            cell_data_2.setCellStyle(cs_field);

            Cell cell_data_3 = row_temp.createCell(1 + cell_index++);
            cell_data_3.setCellValue(q.getCatalogId());
            cell_data_3.setCellStyle(cs_field);

            Cell cell_data_4 = row_temp.createCell(1 + cell_index++);
            cell_data_4.setCellValue(q.getRemark());
            cell_data_4.setCellStyle(cs_field);

            Cell cell_data_5 = row_temp.createCell(1 + cell_index++);
            cell_data_5.setCellValue(q.getSubject());
            cell_data_5.setCellStyle(cs_field);

            Cell cell_data_6 = row_temp.createCell(1 + cell_index++);
            cell_data_6.setCellValue(q.getPicture());
            cell_data_6.setCellStyle(cs_field);

            Cell cell_data_7 = row_temp.createCell(1 + cell_index++);
            cell_data_7.setCellValue(q.getAnalysis());
            cell_data_7.setCellStyle(cs_field);

            Cell cell_data_8 = row_temp.createCell(1 + cell_index++);
            cell_data_8.setCellValue(q.getType());
            cell_data_8.setCellStyle(cs_field);

            Cell cell_data_9 = row_temp.createCell(1 + cell_index++);
            cell_data_9.setCellValue(q.getDifficulty());
            cell_data_9.setCellStyle(cs_field);

            Cell cell_data_10 = row_temp.createCell(1 + cell_index++);
            cell_data_10.setCellValue(q.getIsClassic());
            cell_data_10.setCellStyle(cs_field);

            Cell cell_data_11 = row_temp.createCell(1 + cell_index++);
            cell_data_11.setCellValue(q.getState());
            cell_data_11.setCellStyle(cs_field);

            Cell cell_data_12 = row_temp.createCell(1 + cell_index++);
            cell_data_12.setCellValue(q.getReviewStatus());
            cell_data_12.setCellStyle(cs_field);
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            wb.write(os);
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os;
    }
}

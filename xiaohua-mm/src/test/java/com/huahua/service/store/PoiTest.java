package com.huahua.service.store;

import com.huahua.config.SpringConfig;
import com.huahua.domain.store.Question;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.*;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class PoiTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testProjectPoi() throws IOException {
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
        sheet.addMergedRegion(new CellRangeAddress(1,1,1,12));

        Row row_1 = sheet.createRow(1);
        Cell cell_1_1 = row_1.createCell(1);
        cell_1_1.setCellValue("在线试题导出信息");
        CellStyle cs_title = wb.createCellStyle();
        cs_title.setAlignment(HorizontalAlignment.CENTER);
        cs_title.setVerticalAlignment(VerticalAlignment.CENTER);
        cell_1_1.setCellStyle(cs_title);

        //制作表头
        String[] fields = {"题目ID","所属公司ID","所属目录ID","题目简介","题干描述",
                "题干配图","题目分析","题目类型","题目难度","是否经典题","题目状态","审核状态"};
        Row row_2 = sheet.createRow(2);
        for (int i = 0; i < fields.length; i++) {
            Cell cell_2_temp = row_2.createCell(1 + i);
            cell_2_temp.setCellValue(fields[i]);

            cell_2_temp.setCellStyle(cs_field);
        }


        //制作数据区
        List<Question> questionList = questionService.findAll();


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


        File file = new File("test.xlsx");
        OutputStream outputStream = new FileOutputStream(file);
        wb.write(outputStream);
        wb.close();
        outputStream.close();
    }
}

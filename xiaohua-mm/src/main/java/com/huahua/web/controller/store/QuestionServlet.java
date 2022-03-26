package com.huahua.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.front.Member;
import com.huahua.domain.store.Catalog;
import com.huahua.domain.store.Company;
import com.huahua.domain.store.ExamineLog;
import com.huahua.domain.store.Question;
import com.huahua.domain.system.User;
import com.huahua.utils.BeanUtil;
import com.huahua.web.controller.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

//uri:/store/question?operation=list

/**
 * @author Huahua
 */
@SuppressWarnings("all")
@WebServlet("/store/question")
@Component
public class QuestionServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String operation = request.getParameter("operation");
        if (list.equals(operation)) {
            try {
                this.list(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (toAdd.equals(operation)) {
            try {
                this.toAdd(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (save.equals(operation)) {
            try {
                this.save(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (toEdit.equals(operation)) {
            try {
                this.toEdit(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (edit.equals(operation)) {
            try {
                this.edit(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (delete.equals(operation)) {
            try {
                this.delete(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("downloadReport".equals(operation)) {
            try {
                this.downloadReport(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("toExamine".equals(operation)) {
            try {
                this.toExamine(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("examine".equals(operation)) {
            try {
                this.examine(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //进入列表页
        //获取数据
        int page = 1;
        int size = 10;
        page = toPage(request, page);
        size = toSize(request, size);
        PageInfo all = questionService.findAll(page, size);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(request, response);
    }


    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //加载企业信息
        List<Company> companyList = companyService.findAll();
        request.setAttribute("companyList", companyList);
        //加载题目类型信息
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("catalogList", catalogList);
        request.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveOrUpdate(request, save);
        //跳转页面
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //加载企业信息
        List<Company> companyList = companyService.findAll();
        request.setAttribute("companyList", companyList);
        //加载题目类型信息
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("catalogList", catalogList);
        //查询要修改的数据
        String id = request.getParameter("id");
        Question question = questionService.findById(id);
        //将数据页面加载到指定区域，供页面获取
        request.setAttribute("question", question);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(request, response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveOrUpdate(request, edit);
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将数据获取到，封装成一个对象
        Question question = BeanUtil.fillBean(request, Question.class);
        //调用业务层接口
        questionService.delete(question);
        //同时删除题干图片
        File file = new File(this.getServletContext().getRealPath("upload"), question.getId());
        if (file.exists()) {
            file.delete();
        }
        //跳转回到页面list
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    private void downloadReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //返回的数据类型为文件xlsx类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = new String("题目信息.xlsx".getBytes(), "iso8859-1");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        //生成报告的文件，然后传递到前端页面
        ByteArrayOutputStream report = questionService.getReport();
        //获取产生响应的流对象
        ServletOutputStream sos = response.getOutputStream();
        //将数据从原始的字节流对象中提取出来写到servlet对应的输出流中
        report.writeTo(sos);
        //将输出流刷新
        sos.flush();
        report.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public void saveOrUpdate(HttpServletRequest request, String op) throws Exception {
        //1.确认该操作是否支持文件上传操作，enctype="multipart/form-data"
        if (ServletFileUpload.isMultipartContent(request)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            //4.从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            //创建一个标记位,标记当前时候有上传文件的操作
            boolean flag = false;
            for (FileItem item : fileItems) {
                if (StringUtils.isNotBlank(item.getName())) {
                    flag = true;
                    break;
                }
            }

            // --处理form表单提交过来的普通数据
            //将数据获取到，封装成一个对象
            Question question = BeanUtil.fillBean(fileItems, Question.class);
            String picture = question.getId();
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            if (save.equals(op)) {
                //调用业务层接口save
                question.setCreateUserId(loginUser.getId());
                picture = questionService.save(question, flag);
            } else {
                questionService.update(question, flag);
            }
            // --处理form表单提交过来的文件数据
            for (FileItem item : fileItems) {
                //5.当前表单是否是文件表单
                if (!item.isFormField()) {
                    //6.从临时存储文件的地方将内容写入到指定位置
                    item.write(new File(this.getServletContext().getRealPath("upload"), picture));
                }
            }
        }
    }

    private void toExamine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Question question = questionService.findById(id);
        request.setAttribute("question", question);
        request.getRequestDispatcher("/WEB-INF/pages/store/question/review.jsp").forward(request, response);
    }

    private void examine(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String questionId = request.getParameter("id");
        String status = request.getParameter("status");
        String comments = request.getParameter("comments");
        comments = new String(comments.getBytes("ISO8859_1"), StandardCharsets.UTF_8);
        ExamineLog examineLog = examineLogService.findByQuestionId(questionId);
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (examineLog == null) {
            examineLog = new ExamineLog();
            examineLog.setQuestionId(questionId);
            examineLog.setStatus(status);
            examineLog.setComments(comments);
            examineLog.setUserId(loginUser.getId());
            examineLogService.save(examineLog);
        } else{
            examineLog.setQuestionId(questionId);
            examineLog.setStatus(status);
            if (!"".equals(comments)) {
                examineLog.setComments(comments);
            }
            examineLog.setUserId(loginUser.getId());
            examineLogService.update(examineLog);
        }
        response.sendRedirect(request.getContextPath() + "/store/question?operation=toExamine&id="+questionId);
    }
}



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
        //???????????????
        //????????????
        int page = 1;
        int size = 10;
        page = toPage(request, page);
        size = toSize(request, size);
        PageInfo all = questionService.findAll(page, size);
        //?????????????????????????????????
        request.setAttribute("page", all);
        //????????????
        request.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(request, response);
    }


    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //??????????????????
        List<Company> companyList = companyService.findAll();
        request.setAttribute("companyList", companyList);
        //????????????????????????
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("catalogList", catalogList);
        request.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(request, response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveOrUpdate(request, save);
        //????????????
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //??????????????????
        List<Company> companyList = companyService.findAll();
        request.setAttribute("companyList", companyList);
        //????????????????????????
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("catalogList", catalogList);
        //????????????????????????
        String id = request.getParameter("id");
        Question question = questionService.findById(id);
        //??????????????????????????????????????????????????????
        request.setAttribute("question", question);
        //????????????
        request.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(request, response);

    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveOrUpdate(request, edit);
        //??????????????????list
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //??????????????????????????????????????????
        Question question = BeanUtil.fillBean(request, Question.class);
        //?????????????????????
        questionService.delete(question);
        //????????????????????????
        File file = new File(this.getServletContext().getRealPath("upload"), question.getId());
        if (file.exists()) {
            file.delete();
        }
        //??????????????????list
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    private void downloadReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //??????????????????????????????xlsx??????
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = new String("????????????.xlsx".getBytes(), "iso8859-1");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        //???????????????????????????????????????????????????
        ByteArrayOutputStream report = questionService.getReport();
        //??????????????????????????????
        ServletOutputStream sos = response.getOutputStream();
        //?????????????????????????????????????????????????????????servlet?????????????????????
        report.writeTo(sos);
        //??????????????????
        sos.flush();
        report.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public void saveOrUpdate(HttpServletRequest request, String op) throws Exception {
        //1.????????????????????????????????????????????????enctype="multipart/form-data"
        if (ServletFileUpload.isMultipartContent(request)) {
            //2.????????????????????????
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //3.Servlet????????????????????????
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            //4.???request???????????????
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            //?????????????????????,??????????????????????????????????????????
            boolean flag = false;
            for (FileItem item : fileItems) {
                if (StringUtils.isNotBlank(item.getName())) {
                    flag = true;
                    break;
                }
            }

            // --??????form?????????????????????????????????
            //??????????????????????????????????????????
            Question question = BeanUtil.fillBean(fileItems, Question.class);
            String picture = question.getId();
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            if (save.equals(op)) {
                //?????????????????????save
                question.setCreateUserId(loginUser.getId());
                picture = questionService.save(question, flag);
            } else {
                questionService.update(question, flag);
            }
            // --??????form?????????????????????????????????
            for (FileItem item : fileItems) {
                //5.?????????????????????????????????
                if (!item.isFormField()) {
                    //6.????????????????????????????????????????????????????????????
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



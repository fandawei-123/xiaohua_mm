package com.huahua.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.huahua.domain.store.QuestionItem;
import com.huahua.utils.BeanUtil;
import com.huahua.web.controller.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

//uri:/store/questionItem?operation=list

/**
 * @author Huahua
 */
@WebServlet("/store/questionItem")
@Component
public class QuestionItemServlet extends BaseServlet {
    private String questionId;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (list.equals(operation)) {
            try {
                this.list(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("saveOrUpdate".equals(operation)) {
            try {
                this.saveOrUpdate(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (toEdit.equals(operation)) {
            try {
                this.toEdit(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (delete.equals(operation)) {
            try {
                this.delete(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } /*else if ("deleteAllItem".equals(operation)){
            try {
                this.deleteAllItem(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        if(questionId == null) {
            questionId = request.getParameter("questionId");
        }
        request.setAttribute("questionId", questionId);
        //获取数据
        PageInfo all = questionItemService.findAll(questionId,1, 100);
        //将数据保存到指定的位置
        request.setAttribute("page", all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(request, response);
    }

    private void saveOrUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
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
            QuestionItem questionItem = BeanUtil.fillBean(fileItems, QuestionItem.class);
            String picture = questionItem.getId();
            this.questionId = questionItem.getQuestionId();
            if(StringUtils.isNotBlank(questionItem.getId())){
                questionItemService.update(questionItem,flag);
            }else {
                picture = questionItemService.save(questionItem,flag);
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
        //跳转回到页面list
        list(request, response);
//        response.sendRedirect(request.getContextPath() + "/store/questionItem?operation=list&questionId="+questionId);
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //查询要修改的数据
        String id = request.getParameter("id");
        QuestionItem questionItem = questionItemService.findById(id);
        //将数据页面加载到指定区域，供页面获取
        request.setAttribute("questionItem", questionItem);
        request.setAttribute("operation",edit);
        //跳转回到页面list
        list(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        //将数据获取到，封装成一个对象
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class);
        //调用业务层接口update
        questionItemService.delete(questionItem);
        //同时删除答案图片
        File file = new File(this.getServletContext().getRealPath("upload"),questionItem.getId());
        if(file.exists()) {
            file.delete();
        }
        //跳转回到页面list
        list(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

//    public void deleteAllItem(HttpServletRequest request) throws Exception{
//        String questionId = request.getParameter("questionId");
//        questionItemService.deleteAllItem(questionId);
//    }
}

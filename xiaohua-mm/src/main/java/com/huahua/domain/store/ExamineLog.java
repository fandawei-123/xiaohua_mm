package com.huahua.domain.store;

import com.huahua.domain.system.User;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Huahua
 */
@Component
public class ExamineLog {
    private String id;
    //评论
    private String comments;

    private String status;

    //审核题目id
    private String questionId;

    //审核人id
    private String userId;



    private Date reviewTime;

    //创建题目人
    private Question question;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

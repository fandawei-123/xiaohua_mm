package com.huahua.domain.store;

import org.springframework.stereotype.Component;

/**
 * @author Huahua
 */
@Component
public class QuestionItem {
    //id
    private String id;
    //题目id
    private String questionId;
    //选项内容
    private String content;
    //选项图片
    private String picture;
    //是否正确答案
    private String isRight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }
}

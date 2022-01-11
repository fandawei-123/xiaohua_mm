package com.huahua.domain.store;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Huahua
 */
@Component
public class Question {
    private String id;
    private String subject;
    private String type;
    private List<QuestionItem> questionItems;

    public List<QuestionItem> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<QuestionItem> questionItems) {
        this.questionItems = questionItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

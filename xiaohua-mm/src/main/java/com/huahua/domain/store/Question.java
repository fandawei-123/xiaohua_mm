package com.huahua.domain.store;

import com.huahua.domain.system.User;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Huahua
 */
@Component
public class Question {
    //题目ID
    private String id;

    //所属企业
    private String companyId;
    //题目所属目录ID
    private String catalogId;

    //题目简介
    private String remark;

    //题干
    private String subject;

    private String picture;

    //题目分析
    private String analysis;

    //题目类型  1:单选，2：多选，3：简答
    private String type;
    //难易程度： 1极易 2容易 3普通  4困难  5极难
    private String difficulty;

    //是否经典面试题 0：否 1：是
    private String isClassic;
    //题目状态  0：不可用  1：可用（只有审核通过的题目才可以设置）
    private String state;

    private String reviewStatus;
    //审核状态  -1 审核不通过  0 审核中   1 审核通过
    private Date createTime;
    private String createUserId;

    private Company company;
    private Catalog catalog;
    private User createUser;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getIsClassic() {
        return isClassic;
    }

    public void setIsClassic(String isClassic) {
        this.isClassic = isClassic;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User user) {
        this.createUser = user;
    }
}

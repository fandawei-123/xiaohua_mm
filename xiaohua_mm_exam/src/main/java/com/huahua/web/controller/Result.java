package com.huahua.web.controller;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 页面返回的结果数据封装对象
 * @author Huahua
 */
@Component
public class Result implements Serializable {
    private String message;
    private Boolean flag;
    private Object data;
    private Integer code;

    public Result() {
    }

    public Result(String message, Object data) {
        this.message = message;
        this.data = data;
        this.code = Code.SUCCESS;
        this.flag = true;
    }

    public Result(String message, Boolean flag, Object data, Integer code) {
        this.message = message;
        this.flag = flag;
        this.data = data;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

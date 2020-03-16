package com.github.jconrad.modules.wx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jconrad.common.utils.Json;

import java.io.Serializable;

/**
 * @author Nifury
 * @date 2017-9-27
 */
@TableName("msg_template")
public class MsgTemplate implements Serializable {

    @TableId(type = IdType.INPUT)
    private String templateId;
    private String title;
    private String data;
    private String url;
    private String color;
    @TableField(value = "`status`")
    private int status;
    @TableField(value = "`name`")
    private String name;

    @Override
    public String toString() {
        return Json.toJsonString(this);
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
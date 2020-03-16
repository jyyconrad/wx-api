package com.github.jconrad.modules.wx.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class WxTagUsers {
    @NotEmpty(message = "标签Id不能为空")
    private Long tagId;
    private Long[] userIds;
    private String[] openIds;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
    }

    public String[] getOpenIds() {
        return openIds;
    }

    public void setOpenIds(String[] openIds) {
        this.openIds = openIds;
    }
}

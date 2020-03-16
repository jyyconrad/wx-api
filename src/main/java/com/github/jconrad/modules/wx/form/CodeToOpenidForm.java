package com.github.jconrad.modules.wx.form;

import com.github.jconrad.common.utils.Json;

import javax.validation.constraints.NotEmpty;

public class CodeToOpenidForm {
    @NotEmpty(message = "code不得为空")
    private String code;
    @Override
    public String toString() {
        return Json.toJsonString(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

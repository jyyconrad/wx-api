package com.github.jconrad.modules.wx.form;

import com.github.jconrad.common.utils.Json;

public class AccountBindForm {
	String phoneNum;
	String idCodeSuffix;
	@Override
	public String toString() {
		return Json.toJsonString(this);
	}
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getIdCodeSuffix() {
		return idCodeSuffix;
	}

	public void setIdCodeSuffix(String idCodeSuffix) {
		this.idCodeSuffix = idCodeSuffix;
	}
}

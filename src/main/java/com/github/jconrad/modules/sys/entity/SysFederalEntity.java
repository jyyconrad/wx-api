package com.github.jconrad.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author jyyconrad
 * @email jyy3638@126.com
 * @date 2020-03-13 11:46:41
 */
@Data
@TableName("sys_federal")
public class SysFederalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 登录账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String accpwd;
	/**
	 * 密钥
	 */
	private String acekey;
	/**
	 * 请求地址
	 */
	private String requestUrl;
	/**
	 * 创建日期
	 */
	private Date created;

}

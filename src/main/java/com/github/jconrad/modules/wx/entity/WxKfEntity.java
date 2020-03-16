package com.github.jconrad.modules.wx.entity;

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
 * @date 2020-03-13 11:46:20
 */
@Data
@TableName("wx_kf")
public class WxKfEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String kfAccount;
	/**
	 * 
	 */
	private String kfHeadimgurl;
	/**
	 * 
	 */
	private Integer kfId;
	/**
	 * 
	 */
	private String kfNick;
	/**
	 * 
	 */
	private String kfWx;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date created;

}

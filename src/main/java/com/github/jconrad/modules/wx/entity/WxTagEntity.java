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
 * @date 2020-03-04 15:08:56
 */
@Data
@TableName("wx_tag")
public class WxTagEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 微信设置标签名
	 */
	private Long wxTagId;
	/**
	 * 
	 */
	private String name;

	private int wxCount;

	private Date syncTime;

}

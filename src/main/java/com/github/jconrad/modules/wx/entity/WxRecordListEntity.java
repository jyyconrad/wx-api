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
@TableName("wx_record_list")
public class WxRecordListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 客服帐号
	 */
	private String worker;
	/**
	 * 客服Id
	 */
	private Long kfId;
	/**
	 * 用户标识
	 */
	private String openid;
	/**
	 * 操作码，2002（客服发送信息），2003（客服接收消息）
	 */
	private String opercode;
	/**
	 * 文字聊天内容
	 */
	private String text;
	/**
	 * 图片聊天内容
	 */
	private String image;
	/**
	 * 语音聊天内容
	 */
	private String voice;
	/**
	 * 视频聊天内容
	 */
	private String video;
	/**
	 * 发送时间
	 */
	private Date time;

}

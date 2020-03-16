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
 * @date 2020-03-04 15:08:57
 */
@Data
@TableName("wx_customer")
public class WxCustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	 */
	private Integer subscribe;
	/**
	 * 用户的标识，对当前公众号唯一
	 */
	private String openid;
	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 */
	private String unionid;
	/**
	 * 用户的昵称
	 */
	private String nickname;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 车牌号
	 */
	private String carNo;
	/**
	 * 车牌号1
	 */
	private String carNo2;
	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	private Integer sex;
	/**
	 * 用户所在城市
	 */
	private String city;
	/**
	 * 用户所在国家
	 */
	private String country;
	/**
	 * 用户所在省
	 */
	private String province;
	/**
	 * 用户头像
	 */
	private String headimgurl;
	/**
	 * 用户关注时间
	 */
	private Date subscribeTime;
	/**
	 * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	 */
	private String remark;
	/**
	 * 
	 */
	private Integer groupid;
	/**
	 * 
	 */
	private String tagidList;
	/**
	 * 
	 */
	private String qrScene;
	/**
	 * 
	 */
	private String qrSceneStr;

	private Date syncTime;

}

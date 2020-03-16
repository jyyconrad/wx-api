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
@TableName("sys_holiday_rotation")
public class SysHolidayRotationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 节假日
	 */
	private Date holiday;
	/**
	 * 值班人员Id
	 */
	private Long rotation;

}

package com.github.jconrad.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.modules.sys.entity.SysHolidayRotationEntity;

import java.util.Map;

/**
 * 
 *
 * @author jyyconrad
 * @email jyy3638@126.com
 * @date 2020-03-13 11:46:41
 */
public interface SysHolidayRotationService extends IService<SysHolidayRotationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


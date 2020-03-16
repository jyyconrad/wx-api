package com.github.jconrad.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.modules.wx.entity.WxCustomerEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jyyconrad
 * @email jyy3638@126.com
 * @date 2020-03-04 15:08:57
 */
public interface WxCustomerService extends IService<WxCustomerEntity> {


    Boolean sync();

    PageUtils queryPage(Map<String, Object> params);

    boolean updateTags(Long uid, Long tagIds);

    List<Map<String, Object>>  queryList(Map<String, Object> params);
}


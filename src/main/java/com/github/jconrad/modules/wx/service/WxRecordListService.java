package com.github.jconrad.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.modules.wx.entity.WxRecordListEntity;

import java.util.Map;

/**
 * 
 *
 * @author jyyconrad
 * @email jyy3638@126.com
 * @date 2020-03-13 11:46:20
 */
public interface WxRecordListService extends IService<WxRecordListEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


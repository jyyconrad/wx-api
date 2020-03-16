package com.github.jconrad.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.modules.wx.entity.WxTagEntity;
import com.github.jconrad.modules.wx.form.WxTagUsers;

import java.util.Map;

/**
 * 
 *
 * @author jyyconrad
 * @email jyy3638@126.com
 * @date 2020-03-04 15:08:56
 */
public interface WxTagService extends IService<WxTagEntity> {

    PageUtils queryPage(Map<String, Object> params);


    void batchSetUsers(WxTagUsers wxTagUsers);
}


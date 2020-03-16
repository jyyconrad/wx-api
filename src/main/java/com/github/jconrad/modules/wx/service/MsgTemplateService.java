package com.github.jconrad.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jconrad.modules.wx.entity.MsgTemplate;
import com.github.jconrad.common.utils.PageUtils;

import java.util.Map;

/**
 * 消息模板
 *
 * @author jconrad
 * @email jconrad@qq.com
 * @date 2019-11-12 18:30:15
 */
public interface MsgTemplateService extends IService<MsgTemplate> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过模板名称查询
     * @param name
     * @return
     */
    MsgTemplate selectByName(String name);
}


package com.github.jconrad.modules.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.modules.wx.entity.TemplateMsgLog;

import java.util.Map;

public interface TemplateMsgLogService extends IService<TemplateMsgLog> {
    PageUtils queryPage(Map<String, Object> params);
    /**
     * 添加访问log到队列中，队列数据会定时批量插入到数据库
     * @param log
     */
    void addLog(TemplateMsgLog log);
}

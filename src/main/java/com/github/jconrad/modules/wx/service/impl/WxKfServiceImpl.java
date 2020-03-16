package com.github.jconrad.modules.wx.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.Query;

import com.github.jconrad.modules.wx.dao.WxKfDao;
import com.github.jconrad.modules.wx.entity.WxKfEntity;
import com.github.jconrad.modules.wx.service.WxKfService;


@Service("wxKfService")
public class WxKfServiceImpl extends ServiceImpl<WxKfDao, WxKfEntity> implements WxKfService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WxKfEntity> page = this.page(
                new Query<WxKfEntity>().getPage(params),
                new QueryWrapper<WxKfEntity>()
        );

        return new PageUtils(page);
    }

}
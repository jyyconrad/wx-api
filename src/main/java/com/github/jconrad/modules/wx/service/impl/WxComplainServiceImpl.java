package com.github.jconrad.modules.wx.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.Query;

import com.github.jconrad.modules.wx.dao.WxComplainDao;
import com.github.jconrad.modules.wx.entity.WxComplainEntity;
import com.github.jconrad.modules.wx.service.WxComplainService;


@Service("wxComplainService")
public class WxComplainServiceImpl extends ServiceImpl<WxComplainDao, WxComplainEntity> implements WxComplainService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WxComplainEntity> page = this.page(
                new Query<WxComplainEntity>().getPage(params),
                new QueryWrapper<WxComplainEntity>()
        );

        return new PageUtils(page);
    }

}
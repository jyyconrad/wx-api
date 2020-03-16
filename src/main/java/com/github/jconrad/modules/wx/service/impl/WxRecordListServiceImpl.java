package com.github.jconrad.modules.wx.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.Query;

import com.github.jconrad.modules.wx.dao.WxRecordListDao;
import com.github.jconrad.modules.wx.entity.WxRecordListEntity;
import com.github.jconrad.modules.wx.service.WxRecordListService;


@Service("wxRecordListService")
public class WxRecordListServiceImpl extends ServiceImpl<WxRecordListDao, WxRecordListEntity> implements WxRecordListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WxRecordListEntity> page = this.page(
                new Query<WxRecordListEntity>().getPage(params),
                new QueryWrapper<WxRecordListEntity>()
        );

        return new PageUtils(page);
    }

}
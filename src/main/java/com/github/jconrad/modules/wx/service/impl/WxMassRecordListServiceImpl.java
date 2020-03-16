package com.github.jconrad.modules.wx.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.Query;

import com.github.jconrad.modules.wx.dao.WxMassRecordListDao;
import com.github.jconrad.modules.wx.entity.WxMassRecordListEntity;
import com.github.jconrad.modules.wx.service.WxMassRecordListService;


@Service("wxMassRecordListService")
public class WxMassRecordListServiceImpl extends ServiceImpl<WxMassRecordListDao, WxMassRecordListEntity> implements WxMassRecordListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WxMassRecordListEntity> page = this.page(
                new Query<WxMassRecordListEntity>().getPage(params),
                new QueryWrapper<WxMassRecordListEntity>()
        );

        return new PageUtils(page);
    }

}
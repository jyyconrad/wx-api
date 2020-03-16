package com.github.jconrad.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.Query;

import com.github.jconrad.modules.wx.dao.SysHolidayRotationDao;
import com.github.jconrad.modules.wx.entity.SysHolidayRotationEntity;
import com.github.jconrad.modules.wx.service.SysHolidayRotationService;


@Service("sysHolidayRotationService")
public class SysHolidayRotationServiceImpl extends ServiceImpl<SysHolidayRotationDao, SysHolidayRotationEntity> implements SysHolidayRotationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysHolidayRotationEntity> page = this.page(
                new Query<SysHolidayRotationEntity>().getPage(params),
                new QueryWrapper<SysHolidayRotationEntity>()
        );

        return new PageUtils(page);
    }

}
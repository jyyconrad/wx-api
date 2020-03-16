package com.github.jconrad.modules.sys.service.impl;

import com.github.jconrad.modules.sys.dao.SysFederalDao;
import com.github.jconrad.modules.sys.entity.SysFederalEntity;
import com.github.jconrad.modules.sys.service.SysFederalService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.Query;



@Service("sysFederalService")
public class SysFederalServiceImpl extends ServiceImpl<SysFederalDao, SysFederalEntity> implements SysFederalService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysFederalEntity> page = this.page(
                new Query<SysFederalEntity>().getPage(params),
                new QueryWrapper<SysFederalEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean saveBatch(Collection<SysFederalEntity> entityList) {
        return false;
    }
}
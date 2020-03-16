package com.github.jconrad.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.github.jconrad.modules.sys.entity.SysHolidayRotationEntity;
import com.github.jconrad.modules.sys.service.SysHolidayRotationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.R;



/**
 * 
 *
 * @author jyyconrad
 * @email jyy3638@126.com
 * @date 2020-03-13 11:46:41
 */
@RestController
@RequestMapping("wx/sysholidayrotation")
public class SysHolidayRotationController {
    @Autowired
    private SysHolidayRotationService sysHolidayRotationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wx:sysholidayrotation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysHolidayRotationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wx:sysholidayrotation:info")
    public R info(@PathVariable("id") Integer id){
		SysHolidayRotationEntity sysHolidayRotation = sysHolidayRotationService.getById(id);

        return R.ok().put("sysHolidayRotation", sysHolidayRotation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wx:sysholidayrotation:save")
    public R save(@RequestBody SysHolidayRotationEntity sysHolidayRotation){
		sysHolidayRotationService.save(sysHolidayRotation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wx:sysholidayrotation:update")
    public R update(@RequestBody SysHolidayRotationEntity sysHolidayRotation){
		sysHolidayRotationService.updateById(sysHolidayRotation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wx:sysholidayrotation:delete")
    public R delete(@RequestBody Integer[] ids){
		sysHolidayRotationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package com.github.jconrad.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jconrad.modules.wx.entity.SysFederalEntity;
import com.github.jconrad.modules.wx.service.SysFederalService;
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
@RequestMapping("wx/sysfederal")
public class SysFederalController {
    @Autowired
    private SysFederalService sysFederalService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wx:sysfederal:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysFederalService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wx:sysfederal:info")
    public R info(@PathVariable("id") Integer id){
		SysFederalEntity sysFederal = sysFederalService.getById(id);

        return R.ok().put("sysFederal", sysFederal);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wx:sysfederal:save")
    public R save(@RequestBody SysFederalEntity sysFederal){
		sysFederalService.save(sysFederal);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wx:sysfederal:update")
    public R update(@RequestBody SysFederalEntity sysFederal){
		sysFederalService.updateById(sysFederal);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wx:sysfederal:delete")
    public R delete(@RequestBody Integer[] ids){
		sysFederalService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

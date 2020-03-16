package com.github.jconrad.modules.wx.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jconrad.modules.wx.entity.WxComplainEntity;
import com.github.jconrad.modules.wx.service.WxComplainService;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.R;



/**
 * 
 *
 * @author jyyconrad
 * @email jyy3638@126.com
 * @date 2020-03-13 11:46:20
 */
@RestController
@RequestMapping("wx/wxcomplain")
public class WxComplainController {
    @Autowired
    private WxComplainService wxComplainService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wx:wxcomplain:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxComplainService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wx:wxcomplain:info")
    public R info(@PathVariable("id") Integer id){
		WxComplainEntity wxComplain = wxComplainService.getById(id);

        return R.ok().put("wxComplain", wxComplain);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wx:wxcomplain:save")
    public R save(@RequestBody WxComplainEntity wxComplain){
		wxComplainService.save(wxComplain);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wx:wxcomplain:update")
    public R update(@RequestBody WxComplainEntity wxComplain){
		wxComplainService.updateById(wxComplain);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wx:wxcomplain:delete")
    public R delete(@RequestBody Integer[] ids){
		wxComplainService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

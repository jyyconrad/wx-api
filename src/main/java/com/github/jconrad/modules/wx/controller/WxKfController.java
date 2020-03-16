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

import com.github.jconrad.modules.wx.entity.WxKfEntity;
import com.github.jconrad.modules.wx.service.WxKfService;
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
@RequestMapping("wx/wxkf")
public class WxKfController {
    @Autowired
    private WxKfService wxKfService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wx:wxkf:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxKfService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wx:wxkf:info")
    public R info(@PathVariable("id") Integer id){
		WxKfEntity wxKf = wxKfService.getById(id);

        return R.ok().put("wxKf", wxKf);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wx:wxkf:save")
    public R save(@RequestBody WxKfEntity wxKf){
		wxKfService.save(wxKf);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wx:wxkf:update")
    public R update(@RequestBody WxKfEntity wxKf){
		wxKfService.updateById(wxKf);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wx:wxkf:delete")
    public R delete(@RequestBody Integer[] ids){
		wxKfService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

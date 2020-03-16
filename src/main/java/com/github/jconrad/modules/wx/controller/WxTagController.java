package com.github.jconrad.modules.wx.controller;

import java.util.Arrays;

import com.github.jconrad.modules.wx.form.WxTagUsers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.jconrad.modules.wx.entity.WxTagEntity;
import com.github.jconrad.modules.wx.service.WxTagService;
import com.github.jconrad.common.utils.R;



/**
 * 
 *
 * @author jyyconrad
 * @email jyy3638@126.com
 * @date 2020-03-04 15:08:56
 */
@RestController
@RequestMapping("wx/wxtag")
public class WxTagController {
    @Autowired
    private WxTagService wxTagService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wx:wxtag:list")
    public R list(){
//        L page = ;

        return R.ok().put("list", wxTagService.list());
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wx:wxtag:info")
    public R info(@PathVariable("id") Integer id){
		WxTagEntity wxTag = wxTagService.getById(id);

        return R.ok().put("wxTag", wxTag);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wx:wxtag:save")
//    @PostMapping("/save")
    public R save(@RequestBody WxTagEntity wxTag){
		wxTagService.save(wxTag);

        return R.ok().put("id",wxTag.getId());
    }

    /**
     * 修改
     */
//    @RequestMapping("/update")
    @PostMapping("/update")
    @RequiresPermissions("wx:wxtag:update")
    public R update(@RequestBody WxTagEntity wxTag){
		wxTagService.updateById(wxTag);

        return R.ok();
    }
    /**
     * 修改
     */
//    @RequestMapping("/update")
    @PostMapping("/batchSetUsers")
    @RequiresPermissions("wx:wxtag:update")
    public R batchSetUsers(@RequestBody WxTagUsers wxTagUsers){
        wxTagService.batchSetUsers(wxTagUsers);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wx:wxtag:delete")
    public R delete(@RequestBody Integer[] ids){

		wxTagService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

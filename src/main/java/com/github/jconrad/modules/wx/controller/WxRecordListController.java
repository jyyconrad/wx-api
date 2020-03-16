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

import com.github.jconrad.modules.wx.entity.WxRecordListEntity;
import com.github.jconrad.modules.wx.service.WxRecordListService;
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
@RequestMapping("wx/wxrecordlist")
public class WxRecordListController {
    @Autowired
    private WxRecordListService wxRecordListService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wx:wxrecordlist:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxRecordListService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wx:wxrecordlist:info")
    public R info(@PathVariable("id") Integer id){
		WxRecordListEntity wxRecordList = wxRecordListService.getById(id);

        return R.ok().put("wxRecordList", wxRecordList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wx:wxrecordlist:save")
    public R save(@RequestBody WxRecordListEntity wxRecordList){
		wxRecordListService.save(wxRecordList);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wx:wxrecordlist:update")
    public R update(@RequestBody WxRecordListEntity wxRecordList){
		wxRecordListService.updateById(wxRecordList);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wx:wxrecordlist:delete")
    public R delete(@RequestBody Integer[] ids){
		wxRecordListService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

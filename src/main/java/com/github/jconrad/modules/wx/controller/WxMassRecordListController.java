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

import com.github.jconrad.modules.wx.entity.WxMassRecordListEntity;
import com.github.jconrad.modules.wx.service.WxMassRecordListService;
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
@RequestMapping("wx/wxmassrecordlist")
public class WxMassRecordListController {
    @Autowired
    private WxMassRecordListService wxMassRecordListService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wx:wxmassrecordlist:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxMassRecordListService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wx:wxmassrecordlist:info")
    public R info(@PathVariable("id") Integer id){
		WxMassRecordListEntity wxMassRecordList = wxMassRecordListService.getById(id);

        return R.ok().put("wxMassRecordList", wxMassRecordList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("wx:wxmassrecordlist:save")
    public R save(@RequestBody WxMassRecordListEntity wxMassRecordList){
		wxMassRecordListService.save(wxMassRecordList);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("wx:wxmassrecordlist:update")
    public R update(@RequestBody WxMassRecordListEntity wxMassRecordList){
		wxMassRecordListService.updateById(wxMassRecordList);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wx:wxmassrecordlist:delete")
    public R delete(@RequestBody Integer[] ids){
		wxMassRecordListService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

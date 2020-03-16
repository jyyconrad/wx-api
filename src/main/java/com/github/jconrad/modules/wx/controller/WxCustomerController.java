package com.github.jconrad.modules.wx.controller;

import java.util.*;

import com.github.jconrad.common.utils.TimeUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.jconrad.modules.wx.entity.WxCustomerEntity;
import com.github.jconrad.modules.wx.service.WxCustomerService;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.R;



/**
 * 
 *
 * @author jyyconrad
 * @email jyy3638@126.com
 * @date 2020-03-04 15:08:57
 */
@RestController
@RequestMapping("wx/wxcustomer")
public class WxCustomerController {
    @Autowired
    private WxCustomerService wxCustomerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("wx:wxcustomer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxCustomerService.queryPage(params);
        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/samplelist")
    @RequiresPermissions("wx:wxcustomer:list")
    public R samplelist(@RequestParam Map<String, Object> params){
        List<Map<String,Object>> maps = wxCustomerService.queryList(params);
        return R.ok().put("list", maps);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("wx:wxcustomer:info")
    public R info(@PathVariable("id") Integer id){
		WxCustomerEntity wxCustomer = wxCustomerService.getById(id);

        return R.ok().put("wxCustomer", wxCustomer);
    }
    /**
     * 信息
     */
    @RequestMapping("/sync")
    @RequiresPermissions("wx:wxcustomer:sync")
    public R syncCustomer(){
        wxCustomerService.sync();
        return R.ok();
    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    @PostMapping("/save")
    @RequiresPermissions("wx:wxcustomer:save")
    public R save(@RequestBody WxCustomerEntity wxCustomer){
		wxCustomerService.save(wxCustomer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @PostMapping("/update")
    @RequiresPermissions("wx:wxcustomer:update")
    public R update(@RequestBody WxCustomerEntity wxCustomer){
		wxCustomerService.updateById(wxCustomer);

        return R.ok();
    }
    @RequestMapping("/setCustomerTag")
    @PostMapping("/setCustomerTag")
    @RequiresPermissions("wx:wxcustomer:update")
    public R setCustomerTag(@RequestParam Long uid,@RequestBody Long tagIds){

            wxCustomerService.updateTags(uid,tagIds);

            return R.ok();

    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("wx:wxcustomer:delete")
    public R delete(@RequestBody Integer[] ids){
		wxCustomerService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

package com.github.jconrad.modules.wx.service.impl;

import com.github.jconrad.common.exception.RRException;
import com.github.jconrad.modules.job.service.ScheduleJobService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.Query;

import com.github.jconrad.modules.wx.dao.WxCustomerDao;
import com.github.jconrad.modules.wx.entity.WxCustomerEntity;
import com.github.jconrad.modules.wx.service.WxCustomerService;


@Service("wxCustomerService")
public class WxCustomerServiceImpl extends ServiceImpl<WxCustomerDao, WxCustomerEntity> implements WxCustomerService {

    @Autowired
    private WxCustomerDao wxCustomerDao;
    @Autowired
    WxMpService wxMpService;

    @Autowired
    ScheduleJobService scheduleJobService;

    @Override
    public Boolean sync() {
        try {
            String next=null;
            List<String> openIds=new ArrayList();
            WxMpUserList wxUserList = wxMpService.getUserService().userList(next);
            openIds=wxUserList.getOpenids();
            while (openIds.size()<wxUserList.getTotal()){
                next=wxUserList.getNextOpenid();
                wxUserList = wxMpService.getUserService().userList(next);
                openIds.addAll(wxUserList.getOpenids());
                if(wxUserList.getCount()==0) break;
            }

            List<WxCustomerEntity> entities=new ArrayList<>();
            for (String openId : openIds) {
                WxCustomerEntity entity =this.getBaseMapper().selectOne(new QueryWrapper<WxCustomerEntity>().eq("openid",openId));
                if(null!=entity) {
                    entity.setSyncTime(new Date());
                    this.updateById(entity);
//                    this.getBaseMapper().update(entity);
                }else{
                     entity = new WxCustomerEntity();
                    entity.setOpenid(openId);
                    entity.setSyncTime(new Date());
                    entities.add(entity);
                    wxCustomerDao.insert(entity);
                }
            }
//            wxCustomerDao.insert(entities)

            scheduleJobService.run("asyncCustomerTask.syncMessage","0 0/30 * * * ?",null);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WxCustomerEntity> queryWrapper=new QueryWrapper<WxCustomerEntity>();
        if(params.containsKey("tags")&&StringUtils.isNotBlank(params.get("tags").toString()))
            queryWrapper=queryWrapper.like("tagid_list",params.get("tags"));
        if(params.containsKey("nickname")&&StringUtils.isNotBlank(params.get("nickname").toString()))
            queryWrapper=queryWrapper.like("nickname",params.get("nickname"));
        IPage<WxCustomerEntity> page = this.page(
                new Query<WxCustomerEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean updateTags(Long uid, Long tagIds) {
        WxCustomerEntity entity=getById(uid);
        if(null==entity) throw new RRException("用户不存在");
        else{
//            wxMpService.getUserTagService().tagListUser()
        }
        return false;
    }

    @Override
    public List<Map<String, Object>>  queryList(Map<String, Object> params) {
        QueryWrapper<WxCustomerEntity> queryWrapper=new QueryWrapper<>();
        if(params.containsKey("name"))
            queryWrapper.eq("nickname",params.get("name"));
        if(params.containsKey("nickname"))
            queryWrapper.eq("nickname",params.get("nickname"));

        if(params.containsKey("cols")&&params.get("cols")!=null){
            String colStr=params.get("cols").toString();
            String[] cols=colStr.split(",");
            queryWrapper.select(cols);
        }else{
            queryWrapper.select("id","openid","mobile","nickname");
        }
        return this.getBaseMapper().selectMaps(queryWrapper);

    }
}
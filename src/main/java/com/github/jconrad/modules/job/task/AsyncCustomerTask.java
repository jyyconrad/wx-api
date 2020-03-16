package com.github.jconrad.modules.job.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.jconrad.common.utils.BeanCopier;
import com.github.jconrad.common.utils.Json;
import com.github.jconrad.modules.wx.entity.WxCustomerEntity;
import com.github.jconrad.modules.wx.entity.WxTagEntity;
import com.github.jconrad.modules.wx.service.WxCustomerService;
import com.github.jconrad.modules.wx.service.WxTagService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//@DisallowConcurrentExecution //作业不并发
@Component
public class AsyncCustomerTask  {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxCustomerService wxCustomerService;
    @Autowired
    private WxTagService wxTagService;
    @Autowired
    WxMpService wxMpService;
    private static final String lang = "zh_CN"; //语言
    @Scheduled(cron = "0 0/30 * * * ?")
    public void syncMessage(){

        Date now=new Date();
        logger.info("syncMessage begin "+now.getTime());
        //前一天
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(now);
        rightNow.add(Calendar.DAY_OF_YEAR,-1);
        now=rightNow.getTime();
            List<WxCustomerEntity> list= wxCustomerService.list(
                    new QueryWrapper<WxCustomerEntity>()
                            .select().le("sync_time",now)
            );
            for (WxCustomerEntity entity : list) {
                try {
                    if(StringUtils.isNotBlank(entity.getOpenid())){
                        WxMpUser user=   wxMpService.getUserService().userInfo(entity.getOpenid(), lang);
                        BeanCopier.copyProperties(user,entity);
                        List<Long> tags=wxMpService.getUserTagService().userTagList(entity.getOpenid());
                        if(tags.size()>0)
                        entity.setTagidList(StringUtils.join(tags,","));
                        entity.setSyncTime(new Date());
                        wxCustomerService.saveOrUpdate(entity);
                    }
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
            }



        logger.info("syncMessage end ");


    }

    @Scheduled(cron = "0 0 1 * * ? ")
    public void syncTag(){
        Date now=new Date();
        logger.info("syncTag begin "+now.getTime());
        //前一天
        List<WxTagEntity> list= wxTagService.list();
        try {
           List<WxUserTag> wxUserTags= wxMpService.getUserTagService().tagGet();
            for (WxUserTag wxUserTag : wxUserTags) {
                Optional<WxTagEntity> optionalWxTagEntity=list.stream().filter(item->wxUserTag.getId().equals(item.getWxTagId())).findFirst();
                WxTagEntity wxTagEntity=null;
                if(optionalWxTagEntity.isPresent()){
                    wxTagEntity= optionalWxTagEntity.get();
                }else{
                    wxTagEntity=new WxTagEntity();
                    list.add(wxTagEntity);
                }
                wxTagEntity.setName(wxUserTag.getName());
                wxTagEntity.setWxTagId(wxUserTag.getId());
                wxTagEntity.setSyncTime(new Date());
                wxTagEntity.setWxCount(wxUserTag.getCount());
            }
            wxTagService.saveOrUpdateBatch(list);

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        logger.info("syncTag end ");
    }

}

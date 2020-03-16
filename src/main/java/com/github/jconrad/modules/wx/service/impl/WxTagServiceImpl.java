package com.github.jconrad.modules.wx.service.impl;

import com.github.jconrad.common.exception.RRException;
import com.github.jconrad.modules.wx.entity.WxCustomerEntity;
import com.github.jconrad.modules.wx.form.WxTagUsers;
import com.github.jconrad.modules.wx.service.WxCustomerService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.Query;

import com.github.jconrad.modules.wx.dao.WxTagDao;
import com.github.jconrad.modules.wx.entity.WxTagEntity;
import com.github.jconrad.modules.wx.service.WxTagService;
import org.springframework.transaction.annotation.Transactional;


@Service("wxTagService")
public class WxTagServiceImpl extends ServiceImpl<WxTagDao, WxTagEntity> implements WxTagService {

    @Autowired
    WxMpService wxMpService;
    @Autowired
    WxCustomerService wxCustomerService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WxTagEntity> page = this.page(
                new Query<WxTagEntity>().getPage(params),
                new QueryWrapper<WxTagEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {

        Boolean rst = false;
        WxTagEntity entity = getById(id);
        if (null != entity && null != entity.getWxTagId()) {
            try {
                rst = wxMpService.getUserTagService().tagDelete(entity.getWxTagId());
                if (!rst)
                    return rst;
                rst = getBaseMapper().deleteById(id) > 0;
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        return rst;
    }
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public List<WxTagEntity> list(){
//        List<WxTagEntity> list=new ArrayList<>();
//        try {
//        List<WxUserTag> userTags=    wxMpService.getUserTagService().tagGet();
//            for (WxUserTag userTag : userTags) {
//                WxTagEntity entity=new WxTagEntity();
//                entity.setWxTagId(userTag.getId());
//                entity.setName(userTag.getName());
//            }
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        Boolean rst = false;
        for (Serializable id : idList) {
            removeById(id);
        }
        return rst;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(WxTagEntity entity) {
        Boolean rst = false;
        try {
            rst = wxMpService.getUserTagService().tagUpdate(entity.getWxTagId(), entity.getName());
            if (!rst)
                getBaseMapper().updateById(entity);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return rst;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(WxTagEntity entity) {

        Boolean rst = false;
        try {
            WxUserTag userTag = wxMpService.getUserTagService().tagCreate(entity.getName());
            if (userTag.getId() != null) {
                entity.setWxTagId(userTag.getId());
                rst = this.getBaseMapper().insert(entity) > 0;
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return rst;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSetUsers(WxTagUsers batchSetUsers) {
        if (null == batchSetUsers.getTagId())
            throw new RRException("请选择标签");
        WxTagEntity tagEntity=getById(batchSetUsers.getTagId());
        if(tagEntity==null) throw new RRException("标签不存在");

        if (null != batchSetUsers.getUserIds()) {
            List<String> wxTagIds = new ArrayList<>();//wxTagUsers.getOpenIds()==null?: Arrays.asList(wxTagUsers.getOpenIds());
            if (batchSetUsers.getOpenIds().length > 0)
                wxTagIds = Arrays.asList(batchSetUsers.getOpenIds());
            else if (batchSetUsers.getOpenIds().length == 0 && batchSetUsers.getUserIds().length > 0) {
                for (Long userId : batchSetUsers.getUserIds()) {
                    WxCustomerEntity entity = wxCustomerService.getOne(new QueryWrapper<WxCustomerEntity>().select("openid").eq("id", userId));
                    if (null != entity)
                        wxTagIds.add(entity.getOpenid());
                }
            }
            if (wxTagIds.size() > 0) {
                try {
                  boolean isSuccess=  wxMpService.getUserTagService().batchTagging(tagEntity.getWxTagId(), wxTagIds.toArray(new String[wxTagIds.size()]));
                  if(!isSuccess)  throw new RRException("添加失败");
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
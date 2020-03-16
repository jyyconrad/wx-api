/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 *
 * 版权所有，侵权必究！
 */

package com.github.jconrad.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jconrad.common.utils.Constant;
import com.github.jconrad.common.utils.PageUtils;
import com.github.jconrad.common.utils.Query;
import com.github.jconrad.modules.sys.dao.SysRoleDao;
import com.github.jconrad.modules.sys.entity.SysMenuEntity;
import com.github.jconrad.modules.sys.entity.SysRoleEntity;
import com.github.jconrad.modules.sys.service.*;
import com.github.jconrad.common.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysMenuService sysMenuService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String roleName = (String)params.get("roleName");
		Long createUserId = (Long)params.get("createUserId");
this.createDefaultRole();
		IPage<SysRoleEntity> page = this.page(
			new Query<SysRoleEntity>().getPage(params),
			new QueryWrapper<SysRoleEntity>()
				.like(StringUtils.isNotBlank(roleName),"role_name", roleName)
				.eq(createUserId != null,"create_user_id", createUserId)
		);

		return new PageUtils(page);
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleEntity role) {
        this.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }


    @Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return baseMapper.queryRoleIdList(createUserId);
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRoleEntity role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户所拥有的菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new RRException("新增角色的权限，已超出你的权限范围");
		}
	}
	@Override
	public SysRoleEntity findByTag(String tagName){
		return this.getBaseMapper().selectOne(new QueryWrapper<SysRoleEntity>().select()
		.eq("rolt_tag",tagName)
		);
	}


	@Override
	public void createDefaultRole() {
		SysRoleEntity entity= findByTag(SysRoleEntity.ROLE_ADMIN);
		entity.setMenuIdList(new ArrayList<>());
		if(entity==null){
			entity=new SysRoleEntity();
			entity.setCreateUserId(1L);
			entity.setRemark("系统自动创建角色");
			entity.setRoleName("系统管理员");
			entity.setRoltTag(SysRoleEntity.ROLE_ADMIN);
			List<Long> menuIds=new ArrayList<>();
			for (SysMenuEntity sysMenuEntity : sysMenuService.list()) {
				menuIds.add(sysMenuEntity.getMenuId());
			}
			entity.setMenuIdList(menuIds);
			this.saveRole(entity);
		}
		 entity= findByTag(SysRoleEntity.ROLE_MANAGER);
		if(entity==null){
			entity=new SysRoleEntity();
			entity.setRoleId(null);
			entity.setCreateUserId(1L);
			entity.setRemark("系统自动创建角色");
			entity.setRoleName("管理");
			entity.setRoltTag(SysRoleEntity.ROLE_MANAGER);
			this.saveRole(entity);
		}
		entity= findByTag(SysRoleEntity.ROLE_ACCOUNTANT);
		if(entity==null){
			entity=new SysRoleEntity();
			entity.setRoleId(null);
			entity.setCreateUserId(1L);
			entity.setRemark("系统自动创建角色");
			entity.setRoleName("财务");
			entity.setRoltTag(SysRoleEntity.ROLE_ACCOUNTANT);
			this.saveRole(entity);
		}
		entity= findByTag(SysRoleEntity.ROLE_Employee);
		if(entity==null){
			entity=new SysRoleEntity();
			entity.setRoleId(null);
			entity.setCreateUserId(1L);
			entity.setRemark("系统自动创建角色");
			entity.setRoleName("人员");
			entity.setRoltTag(SysRoleEntity.ROLE_Employee);
			this.saveRole(entity);
		}
	}

}

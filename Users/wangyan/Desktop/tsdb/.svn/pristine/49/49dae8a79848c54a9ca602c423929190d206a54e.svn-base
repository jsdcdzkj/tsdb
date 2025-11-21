package com.jsdc.tsdb.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.core.base.BaseService;
import com.jsdc.tsdb.dao.SysRoleDao;
import com.jsdc.tsdb.model.SysMenu;
import com.jsdc.tsdb.model.SysRole;
import com.jsdc.tsdb.model.SysRoleMenu;
import com.jsdc.tsdb.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class SysRoleService extends BaseService<SysRoleDao,SysRole> {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    /**
    *角色新增/修改接口
    * Author wzn
    * Date 2022/9/28 9:37
    */
    public ResultInfo saveRole(SysRole sysRole){
        SysUser currUser = sysUserService.getUser();
        if(null != sysRole.getId()){
            //修改
            sysRole.setUpdateUser(currUser.getId()+"");
            sysRole.setUpdateTime(new Date());
            //删除老的菜单关联数据
            int flag = sysRoleMenuService.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", sysRole.getId()));

        }else{
            //新增
            sysRole.setCreateUser(currUser.getId()+"");
            sysRole.setCreateTime(new Date());
        }
        sysRole.setIs_del("0");
        sysRole.insertOrUpdate();
        //新增角色菜单关联数据
        for (String menuId:sysRole.getMenuIds()){
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRole_id(sysRole.getId()+"");
            sysRoleMenu.setIs_del("0");
            sysRoleMenu.setMenu_id(menuId);
            sysRoleMenu.insert();
        }
        return ResultInfo.success();
    }

}

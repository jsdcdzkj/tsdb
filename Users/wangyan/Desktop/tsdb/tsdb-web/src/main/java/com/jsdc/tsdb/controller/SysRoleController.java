package com.jsdc.tsdb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.model.SysRole;
import com.jsdc.tsdb.model.SysRoleMenu;
import com.jsdc.tsdb.service.SysMenuService;
import com.jsdc.tsdb.service.SysRoleMenuService;
import com.jsdc.tsdb.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
*
* Author wzn
* Date 2022/9/27 11:05
*/
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService ;





    /**
     * 获取角色
     * @param roleName
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/getList")
    private ResultInfo getList(String roleName, Integer pageNo, Integer pageSize){
        QueryWrapper qw = new QueryWrapper();
        qw.eq("is_del","0");

        if(StringUtils.hasText(roleName)){
            qw.like("role_name",roleName);
        }
        IPage<SysRole> pageinfo = sysRoleService.selectPage(new Page<>(pageNo,pageSize),qw);
        return ResultInfo.success(pageinfo);
    }

    /**
     * 获取所有角色
     * @return
     */
    @PostMapping("/getAllRoles")
    private ResultInfo getAllRoles(SysRole sysRole){
        PageHelper.startPage(sysRole.getPage(), sysRole.getLimit());
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>() ;
        queryWrapper.eq("is_del",0) ;
        if(StringUtils.hasText(sysRole.getRole_name())){
            queryWrapper.like("role_name",sysRole.getRole_name()) ;
        }
        queryWrapper.orderByDesc("createTime") ;
        List<SysRole> sysRoles = sysRoleService.selectList(queryWrapper);

        return ResultInfo.success(new PageInfo(sysRoles));
    }




    /**
     * 删除角色
     * @return
     */
    @PostMapping("/delRole")
    public ResultInfo delRole(String roleId) {
        SysRole sysRole = sysRoleService.selectById(roleId);
        if(sysRole != null){
            sysRole.setIs_del("1");
            sysRole.updateById();
        }else {
            return ResultInfo.error("无此角色！");
        }
        return ResultInfo.success();
    }


    /**
    *新增/修改角色接口
    * Author wzn
    * Date 2022/9/28 9:38
    */
    @PostMapping("/saveRole")
    public ResultInfo saveMenu(@RequestBody SysRole sysRole) {
        return sysRoleService.saveRole(sysRole);
    }


    /**
    *菜单树形图
    * Author wzn
    * Date 2022/10/9 9:52
    */
    @PostMapping("/tree")
    public ResultInfo tree() {
        return ResultInfo.success(sysMenuService.tree());
    }


    /**
     *角色修改树形图回显
     * Author wzn
     * Date 2022/10/9 9:25
     */
    @PostMapping("/roleEditTree")
    public ResultInfo roleEditTree(String roleId) {
        return ResultInfo.success(sysMenuService.roleEditTree(roleId));
    }


}

package com.jsdc.tsdb.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsdc.tsdb.common.utils.GlobalData;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.core.base.BaseController;
import com.jsdc.tsdb.model.SysDept;
import com.jsdc.tsdb.model.SysRole;
import com.jsdc.tsdb.model.SysUser;
import com.jsdc.tsdb.service.SysDeptService;
import com.jsdc.tsdb.service.SysRoleService;
import com.jsdc.tsdb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 用户
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysRoleService sysRoleService;


    @RequestMapping(value = "reset.do", method = RequestMethod.GET)
    public String reset() {
        return "edit_pwd";
    }

    @RequestMapping(value = "info.do", method = RequestMethod.GET)
    public String info(Model model) {
        model.addAttribute("sysUser", sysUserService.getUser());
        return "profile";
    }

    @RequestMapping(value = "index.do", method = RequestMethod.GET)
    public String open(Model model) {
        model.addAttribute("deptList", sysDeptService.selectList(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getIs_delete, GlobalData.ISDEL_NO).eq(SysDept::getIs_enable,GlobalData.ISENABLE_YES)));
        model.addAttribute("roleList", sysRoleService.selectList(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getIs_del, GlobalData.ISDEL_NO)));
        return "html/system/renyuan/index";
    }

    @RequestMapping(value = "edit.do", method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("deptList", sysDeptService.selectList(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getIs_delete, GlobalData.ISDEL_NO).eq(SysDept::getIs_enable,GlobalData.ISENABLE_YES)));
        model.addAttribute("roleList", sysRoleService.selectList(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getIs_del, GlobalData.ISDEL_NO)));
        return "html/system/renyuan/edit";
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "changePassword.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo changePassword(String pass, String newPassWord) {
        SysUser user = sysUserService.getUser();
        if (!user.getPass().equals(pass)) {
            return ResultInfo.error("旧密码输入不正确");
        } else {
            user.setTempPass(pass);
            user.setPass(newPassWord);
            user.updateById();
            return ResultInfo.success("修改密码成功");
        }
    }

    /**
     * 分页数据
     *
     * @return
     */
    @RequestMapping(value = "pageList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo pageList(SysUser bean) {
        PageHelper.startPage(bean.getPage(), bean.getLimit());
        List<SysUser> sysUserList = sysUserService.getList(bean);
        Map<Integer, String> deptMap = sysDeptService.selectList(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getIs_delete, GlobalData.ISDEL_NO)).stream().collect(Collectors.toMap(SysDept::getId, SysDept::getDept_name));
        Map<Integer, String> roleMap = sysRoleService.selectList(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getIs_del, GlobalData.ISDEL_NO)).stream().collect(Collectors.toMap(SysRole::getId, SysRole::getRole_name));
        for (SysUser sysUser : sysUserList) {
            sysUser.setDept_name(deptMap.get(sysUser.getDept_id()));
            sysUser.setRole_name(roleMap.get(sysUser.getRole_id()));
        }
        return ResultInfo.success(new PageInfo(sysUserList));
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "edit.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo edit(@RequestBody SysUser bean) {
        return sysUserService.edit(bean);
    }

}

package com.jsdc.tsdb.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.core.base.BaseController;
import com.jsdc.tsdb.model.SysDept;
import com.jsdc.tsdb.model.SysUser;
import com.jsdc.tsdb.service.SysDeptService;
import com.jsdc.tsdb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 组织部门
 */
@Controller
@RequestMapping("sysDept")
public class SysDeptController extends BaseController {

    @Autowired
    private SysDeptService sysdeptService;
    @Autowired
    private SysUserService sysUserService;


    @RequestMapping(value = "index.do", method = RequestMethod.GET)
    public String open() {
        return "html/system/bumen/index";
    }

    @RequestMapping(value = "edit.do", method = RequestMethod.GET)
    public String edit() {
        return "html/system/bumen/edit";
    }

    /**
     * 分页数据
     *
     * @return
     */
    @RequestMapping(value = "pageList.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo pageList(SysDept bean) {
        PageHelper.startPage(bean.getPage(), bean.getLimit());
        List<SysDept> sysDeptList = sysdeptService.getList(bean);
        for (SysDept sysDept : sysDeptList) {
            Integer sysUserCount = sysUserService.selectCount(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getDept_id, sysDept.getId()));
            sysDept.setSysUserCount(sysUserCount);
        }
        return ResultInfo.success(new PageInfo(sysDeptList));
    }

    /**
     * 更新
     */
    @RequestMapping(value = "edit.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo edit(@RequestBody SysDept bean) {
        return sysdeptService.edit(bean);
    }

}

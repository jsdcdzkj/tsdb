package com.jsdc.tsdb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsdc.tsdb.model.Cron;
import com.jsdc.tsdb.model.SysRole;
import com.jsdc.tsdb.service.CronService;
import com.jsdc.tsdb.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/font")
public class ToController {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private CronService cronService;

    /**
     * 跳转角色页面
     * Author wzn
     * Date 2022/10/8 14:00
     */
    @RequestMapping(value = "toRole", method = RequestMethod.GET)
    public String toRole() {
        return "html/system/role/index";
    }

    @RequestMapping(value = "toRoleAdd", method = RequestMethod.GET)
    public String toRoleAdd() {
        return "html/system/role/add";
    }

    @RequestMapping(value = "toRoleEdit", method = RequestMethod.GET)
    public String toRoleEdit(String id, Model model) {
        SysRole sysRole = sysRoleService.selectById(id);
        model.addAttribute("role", sysRole);
        return "html/system/role/edit";
    }


    @RequestMapping(value = "toCaseBfAdd", method = RequestMethod.GET)
    public String toCaseBf(Model model) {
        QueryWrapper queryWrapper = new QueryWrapper();

        List<Cron> cronList = cronService.selectList(queryWrapper);
        if (!cronList.isEmpty()) {
            model.addAttribute("cron", cronList.get(0));
        }
        return "/html/tsspgl/tsspbf/add";
    }


    @RequestMapping(value = "toSprecord", method = RequestMethod.GET)
    public String toSprecord() {
        return "html/baobiao/sprecord/index";
    }

}

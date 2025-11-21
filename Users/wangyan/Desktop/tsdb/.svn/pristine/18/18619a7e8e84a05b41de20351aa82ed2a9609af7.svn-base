package com.jsdc.tsdb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.model.Cron;
import com.jsdc.tsdb.model.SysRole;
import com.jsdc.tsdb.service.CronService;
import com.jsdc.tsdb.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*定时任务
* Author wzn
* Date 2022/9/28 10:11
*/
@RestController
@RequestMapping("/cron")
public class CronController {

    @Autowired
    private CronService cronService;

    /**
    *新增定时任务
    * Author wzn
    * Date 2022/9/28 10:11
    */
    @PostMapping("/save")
    private ResultInfo save(@RequestBody Cron cron){
        cronService.saveCron(cron);
        return ResultInfo.success();
    }



    /**
    *定时任务详情
    * Author wzn
    * Date 2022/9/28 10:13
    */
    @PostMapping("/info")
    private ResultInfo info(){
        QueryWrapper<Cron> queryWrapper = new QueryWrapper<>() ;
        List<Cron> crons = cronService.selectList(queryWrapper) ;
        Cron cron = null ;
        if(null != crons && crons.size()>0){
            cron = crons.get(0) ;
        }
        return ResultInfo.success(cron);
    }

}

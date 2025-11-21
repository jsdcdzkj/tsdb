package com.jsdc.tsdb.common.task;


import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsdc.tsdb.model.Cron;
import com.jsdc.tsdb.service.CronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Order(value = 2) // 加载顺序，值越小，优先级越高
@Component
public class TaskRun implements CommandLineRunner {
    @Autowired
    private CronService cronService ;

    /**
    *项目启动时，如有已启用的定时任务，自动激活
    * Author wzn
    * Date 2022/10/9 14:18
    */
    @Override
    public void run(String... args) throws Exception {

        //动态备份
        QueryWrapper queryWrapper = new QueryWrapper() ;
        List<Cron> cronList = cronService.selectList(queryWrapper) ;
        if(null != cronList){
            if("1".equals(cronList.get(0).getIs_use())){
                //启动定时任务
                CronUtil.setMatchSecond(true);
                String jobId = CronUtil.schedule(cronList.get(0).getExpression(), new Task() {
                    @Override
                    public void execute() {
                        //todo 调用备份接口
                        System.out.println("----调用备份接口----");
                    }
                });
                CronUtil.start();
                cronList.get(0).setJob_id(jobId);
                cronService.updateById(cronList.get(0)) ;
            }
        }

    }
}
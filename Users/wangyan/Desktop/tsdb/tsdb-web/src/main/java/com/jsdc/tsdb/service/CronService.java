package com.jsdc.tsdb.service;


import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.http.webservice.SoapClient;
import com.jsdc.tsdb.core.base.BaseService;
import com.jsdc.tsdb.dao.CronDao;
import com.jsdc.tsdb.model.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;


@Service
public class CronService extends BaseService<CronDao, Cron> {

    @Autowired
    private SysUserService sysUserService ;

//    @Value("${cron}")
//    private String cron;

    /**
    *新增定时任务
    * Author wzn
    * Date 2022/9/28 9:50
    */
    public void saveCron(Cron cron){
        if(null != cron.getId()){
            //删除旧数据
            Cron cron1 = selectById(cron.getId()) ;
            if(StringUtils.hasText(cron1.getJob_id())){
                CronUtil.stop();
                //关掉定时任务
                CronUtil.remove(cron.getJob_id());
            }
            this.deleteById(cron.getId()) ;


        }

       //新增数据
        String expression ="" ;
        //分钟
        String mm= cron.getThe_backup_time().substring(3);
        //小时
        String hh= cron.getThe_backup_time().substring(0,2);
        //
        //每天26 24 8 * * ?
        if("0".equals(cron.getThe_backup_frequency())){
            expression = "0 "+mm+" "+hh+" * * ?" ;
        }else {
            //* 14 8 * * 4
            expression = "* "+mm+" "+hh+" * * "+cron.getThe_backup_frequency() ;
        }
        Cron cron1 = new Cron() ;
        if("1".equals(cron.getIs_use())){
            //启动定时任务
            CronUtil.setMatchSecond(true);

            String jobId = CronUtil.schedule(expression, new Task() {
                @Override
                public void execute() {
                    //todo 调用备份接口
                    System.out.println("----调用备份接口----");
                }
            });

            CronUtil.start();
            cron1.setJob_id(jobId);
        }

            cron1.setIs_use(cron.getIs_use());
            cron1.setCreateTime(new Date());
            cron1.setCreateUser(sysUserService.getUser().getId()+"");
            cron1.setExpression(expression);
            cron1.setThe_backup_time(cron.getThe_backup_time());
            cron1.setThe_backup_frequency(cron.getThe_backup_frequency());
            cron1.setIs_del("0");
            cron1.insert() ;

    }



}

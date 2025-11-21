package com.jsdc.tsdb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
*动态定时任务
* Author wzn
* Date 2022/9/28 8:50
*/
@Data
@Entity(name = "cron")
@TableName("cron")
public class Cron extends Model<Cron> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    //是否开启定时  0否  1是
    private String is_use ;

    //备份频率 0每天  1周一 2周二 3周三  4周四  5周五 6周六  7周天
    private String the_backup_frequency;

    //备份时间
    private String the_backup_time;

    //cron表达式
    private String expression;

    //定时任务ID
    private String job_id;



    private String createUser;      //创建人
    private Date createTime;      //创建时间
    private String updateUser;      //创建用户
    private Date updateTime;      //创建时间
    private String is_del;          //删除标志
}

package com.jsdc.tsdb.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 点播日志表
 */
@Data
public class BizCaseRecordVo {

    //点播人
    private String   onDemandOf;
    private String trial_name;//庭审名称
    private String case_code;//案件编号
    private String onDemandOfTime;//点播时间

    private String trial_time;//庭审时间
    private String case_type;//案件类型
    private String undertake_dept;//承办部门
    @Transient
    @TableField(exist = false)
    private Integer page = 1;

    @Transient
    @TableField(exist = false)
    private Integer limit = 10;

}

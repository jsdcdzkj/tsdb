package com.jsdc.tsdb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 庭审案件信息
 */
@Entity
@TableName("biz_case_info")
@Table(name = "biz_case_info")
@DynamicInsert
@DynamicUpdate
@Data
public class BizCaseInfo extends Model<BizCaseInfo> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String trial_name;//庭审名称
    private String case_code;//案件编号
    private String trial_time;//庭审时间
    private String plaintiff;//原告
    private String defendant;//被告
    private String case_name;//案件名称
    private String undertake_dept;//承办部门
    private String presiding_judges;//主审法官
    private String case_type;//案件类型
    private String case_time;//立案时间
    private String case_memo;//案由描述
    private String is_pub;//是否公开
    private String backup_status;//备份状态
    private Integer video_num;//录像份数
    private Integer record_num;//笔录份数

    @Transient
    @TableField(exist = false)
    private Integer page = 1;
    @Transient
    @TableField(exist = false)
    private Integer limit = 10;
    @Transient
    @TableField(exist = false)
    private String num;//点击次数
    @Transient
    @TableField(exist = false)
    private String date;//时间范围
    @Transient
    @TableField(exist = false)
    private String user_name;//法官姓名
}

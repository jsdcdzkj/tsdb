package com.jsdc.tsdb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 部门表
 */
@Entity
@Table(name = "sys_dept")
@DynamicInsert
@DynamicUpdate
@Data
public class SysDept extends Model<SysDept> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //部门名称
    private String dept_name;
    //描述
    private String describes;
    //是否删除
    @Column(columnDefinition = "Integer(1) default 0")
    private Integer is_delete = 0;
    //是否启用
    @Column(columnDefinition = "Integer(1) default 1")
    private Integer is_enable = 1;

    @Transient
    @TableField(exist = false)
    private Integer page = 1;
    @Transient
    @TableField(exist = false)
    private Integer limit = 10;
    @Transient
    @TableField(exist = false)
    private Integer sysUserCount = 0;

    private String createUser;      //创建人
    private Date createTime;      //创建时间
    private String updateUser;      //更新用户
    private Date updateTime;      //更新时间
}

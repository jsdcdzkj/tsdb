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
import java.io.*;

/**
 * 用户表
 */
@Entity
@TableName("sys_user")
@Table(name = "sys_user")
@Data
public class SysUser extends Model<SysUser> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //登陆密码
    private String pass;
    //登录名
    private String login_name;
    //姓名
    private String name;
    //性别
    private String sex;
    //手机
    private String phone;
    //所属部门
    private Integer dept_id;
    //所属部门名称
    private String dept_name;
    //所属角色
    private Integer role_id;
    //所属角色名称
    private String role_name;
    //旧密码
    private String tempPass;
    //是否删除
    @Column(columnDefinition = "Integer(1) default 0")
    private Integer is_delete;
    //是否启用
    @Column(columnDefinition = "Integer(1) default 1")
    private Integer is_enable;

    @Transient
    @TableField(exist = false)
    private Integer page = 1;
    @Transient
    @TableField(exist = false)
    private Integer limit = 10;
    @Transient
    @TableField(exist = false)
    private String query_where;

}

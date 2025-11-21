package com.jsdc.tsdb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: SysUserRole
 * @Description: 用户角色表
 * @Date: 2022/9/21 11:25
 * @Author: wr
 */

@Entity
@TableName("sys_user_role")
@Table(name = "sys_user_role")
@DynamicInsert
@DynamicUpdate
@Data
public class SysUserRole extends Model<SysUser> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    //用户id
    private Integer userId;
    //角色id
    private Integer roleId;

}

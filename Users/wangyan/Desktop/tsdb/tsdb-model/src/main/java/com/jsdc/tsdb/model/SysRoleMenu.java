package com.jsdc.tsdb.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysRoleMenu
 * @Description
 * @Author xujian
 * @Date 2022/3/28 12:54
 * @Version 1.0
 */
@Data
@Entity(name = "sys_role_menu")
@TableName("sys_role_menu")
public class SysRoleMenu extends Model<SysRoleMenu> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private String role_id;//角色编号
    private String menu_id;//菜单编号

    private String createUser;      //创建人
    private Date createTime;      //创建时间
    private String updateUser;      //创建用户
    private Date updateTime;      //创建时间
    private String is_del;          //删除标志
}

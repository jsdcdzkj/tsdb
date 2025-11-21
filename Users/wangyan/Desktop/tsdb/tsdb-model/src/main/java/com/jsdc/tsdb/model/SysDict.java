package com.jsdc.tsdb.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: SysDict
 * @Description: 字典表
 * @Date: 2022/9/21 11:26
 * @Author: wr
 */
@Entity
@TableName("SysDict")
@Table(name = "SysDict")
@DynamicInsert
@DynamicUpdate
@Data
public class SysDict extends Model<SysDict> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //字典名
    @Column(nullable = false)
    private String label;
    //字典值
    @Column(nullable = false)
    private String value;
    //类别
    @Column(nullable = false)
    private String dict_type;
    //描述
    @Column(nullable = true)
    private String description;
    //是否删除
    @Column(nullable = false, columnDefinition = "INT default 0")
    private Integer isDel;

}

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
 * 案件笔录
 */
@Entity
@TableName("biz_court_record")
@Table(name = "biz_court_record")
@DynamicInsert
@DynamicUpdate
@Data
public class BizCourtRecord extends Model<BizCourtRecord> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String case_code;//案件编号
    private String record_url;//笔录地址
    private String record_name;//笔录名称
    private String backup_type;//备份方式
}

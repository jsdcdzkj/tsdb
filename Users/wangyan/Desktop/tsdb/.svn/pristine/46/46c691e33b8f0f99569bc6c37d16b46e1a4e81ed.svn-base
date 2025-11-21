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
 * 案件查看记录
 */
@Entity
@TableName("biz_case_record")
@Table(name = "biz_case_record")
@DynamicInsert
@DynamicUpdate
@Data
public class BizCaseRecord extends Model<BizCaseRecord> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String case_code;//案件编号
    private Integer user_id;//用户ID
    private String view_time;//查看时间
}

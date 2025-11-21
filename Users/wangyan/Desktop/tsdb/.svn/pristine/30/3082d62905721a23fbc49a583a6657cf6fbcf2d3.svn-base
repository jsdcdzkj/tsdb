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
 * 案件录像
 */
@Entity
@TableName("biz_case_video")
@Table(name = "biz_case_video")
@DynamicInsert
@DynamicUpdate
@Data
public class BizCaseVideo extends Model<BizCaseVideo> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String case_code;//案件编号
    private String start_time;//开始备份时间
    private String end_time;//结束备份时间
    private String video_url;//视频地址
    private String video_name;//视频名称
    private String backup_type;//备份方式
    private String definition;//清晰度

}

package com.jsdc.tsdb.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SysMenu
 * @Description
 * @Author xujian
 * @Date 2022/3/28 12:54
 * @Version 1.0
 */
@Data

public class SysMenuVo {

    private Integer id;

    private String title;

    private String spread;

    private boolean checked;

    private String parent_id;//父级ID

    private List<SysMenuVo> children ;

}

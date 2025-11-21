package com.jsdc.tsdb.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jsdc.tsdb.model.Cron;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CronMapper extends BaseMapper<Cron> {


}

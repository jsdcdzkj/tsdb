package com.jsdc.tsdb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsdc.tsdb.core.base.BaseService;
import com.jsdc.tsdb.dao.OnTheLogDao;
import com.jsdc.tsdb.mapper.OnTheLogMapper;
import com.jsdc.tsdb.model.vo.BizCaseRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class OnTheLogService extends BaseService<OnTheLogDao, BizCaseRecordVo> {





}

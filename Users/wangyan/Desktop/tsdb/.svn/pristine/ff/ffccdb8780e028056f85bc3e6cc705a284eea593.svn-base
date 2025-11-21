package com.jsdc.tsdb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jsdc.tsdb.dao.BizCaseInfoDao;
import com.jsdc.tsdb.model.BizCaseInfo;
import com.jsdc.tsdb.model.SysUser;
import com.jsdc.tsdb.model.vo.WorkloadVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * ClassName: BizCaseInfoMapper
 * Description:
 * date: 2022/9/28 13:41
 *
 * @author xj
 */
@Mapper
public interface BizCaseInfoMapper extends BaseMapper<BizCaseInfo> {
    @SelectProvider(type = BizCaseInfoDao.class, method = "selectCaseRecord")
    List<BizCaseInfo> getCaseInfos(BizCaseInfo bizCaseInfo, SysUser sysUser);

    @SelectProvider(type = BizCaseInfoDao.class, method = "getTotal")
    WorkloadVo getTotal(BizCaseInfo bizCaseInfo);

    @SelectProvider(type = BizCaseInfoDao.class, method = "getTypeRatio")
    List<WorkloadVo> getTypeRatio(BizCaseInfo bizCaseInfo);

    @SelectProvider(type = BizCaseInfoDao.class, method = "getDeptRatio")
    List<WorkloadVo> getDeptRatio(BizCaseInfo bizCaseInfo);

    @SelectProvider(type = BizCaseInfoDao.class, method = "getWorkload")
    List<WorkloadVo> getWorkload(BizCaseInfo bizCaseInfo);
}

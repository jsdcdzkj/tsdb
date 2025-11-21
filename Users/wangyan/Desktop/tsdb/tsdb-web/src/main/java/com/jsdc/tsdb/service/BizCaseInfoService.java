package com.jsdc.tsdb.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.jsdc.tsdb.common.utils.GlobalData;
import com.jsdc.tsdb.common.exception.CustomException;
import com.jsdc.tsdb.common.utils.ZipUtils;
import com.jsdc.tsdb.core.base.BaseService;
import com.jsdc.tsdb.dao.BizCaseInfoDao;
import com.jsdc.tsdb.mapper.*;
import com.jsdc.tsdb.model.*;
import com.jsdc.tsdb.model.vo.WorkloadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yanbin123
 * @since 2019-08-14
 */
@Service
@Transactional
public class BizCaseInfoService extends BaseService<BizCaseInfoDao, BizCaseInfo> {
    @Autowired
    private BizCaseInfoMapper bizCaseInfoMapper;

    @Autowired
    private BizCaseVideoMapper bizCaseVideoMapper;

    @Autowired
    private BizCourtRecordMapper bizCourtRecordMapper;

    @Autowired
    private BizCaseRecordMapper bizCaseRecordMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Value("${UPLOAD_PATH}")
    private String upload_path;

    @Value("${dow_path}")
    private String dow_path;

    public List<BizCaseInfo> getCaseInfos(BizCaseInfo bizCaseInfo, SysUser sysUser) {
        PageHelper.startPage(bizCaseInfo.getPage(), bizCaseInfo.getLimit());
        return bizCaseInfoMapper.getCaseInfos(bizCaseInfo, sysUser);
    }

    public WorkloadVo getTotal(BizCaseInfo bizCaseInfo) {
        WorkloadVo vo = bizCaseInfoMapper.getTotal(bizCaseInfo);
        LambdaQueryWrapper<BizCaseRecord> bizCaseRecordLambda = new LambdaQueryWrapper<>();
        if(!StrUtil.hasEmpty(bizCaseInfo.getDate())){
            String[] date = bizCaseInfo.getDate().split(" - ");
            bizCaseRecordLambda.ge(BizCaseRecord::getView_time,date[0] + " 00:00:00'");
            bizCaseRecordLambda.le(BizCaseRecord::getView_time,date[1] + " 23:59:59'");
        }
        Integer bizCourtRecordCount = bizCaseRecordMapper.selectCount(bizCaseRecordLambda);
        Integer sysUserCount = sysUserMapper.selectCount(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getIs_delete, GlobalData.ISDEL_NO).eq(SysUser::getIs_enable, GlobalData.ISENABLE_YES));
        vo.setTotal(bizCourtRecordCount);
        vo.setUser_num(sysUserCount);
        return vo;
    }

    public List<WorkloadVo> getTypeRatio(BizCaseInfo bizCaseInfo) {
        return bizCaseInfoMapper.getTypeRatio(bizCaseInfo);
    }

    public List<WorkloadVo> getDeptRatio(BizCaseInfo bizCaseInfo) {
        return bizCaseInfoMapper.getDeptRatio(bizCaseInfo);
    }

    public List<WorkloadVo> getWorkload(BizCaseInfo bizCaseInfo) {
        PageHelper.startPage(bizCaseInfo.getPage(), bizCaseInfo.getLimit());
        return bizCaseInfoMapper.getWorkload(bizCaseInfo);
    }

    /**
     * 下载视频和笔录
     * Author wzn
     * Date 2022/10/12 16:53
     */
    public String dowVideoAndCount(String caseCode) {
        //查询视频
        QueryWrapper<BizCaseVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("case_code", caseCode);
        List<BizCaseVideo> bizCaseVideoList = bizCaseVideoMapper.selectList(queryWrapper);

        //查询笔录
        QueryWrapper<BizCourtRecord> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("case_code", caseCode);
        List<BizCourtRecord> bizCaseVideoList2 = bizCourtRecordMapper.selectList(queryWrapper2);


        String uuid = IdUtil.simpleUUID();
        String name = dow_path + uuid + ".zip";
        List<Map<String, Object>> sourceMapList = new ArrayList<Map<String, Object>>();

        if (null != bizCaseVideoList) {
            for (BizCaseVideo bizCaseVideo : bizCaseVideoList) {
                if (null != bizCaseVideo) {
                    sourceMapList.add(ZipUtils.getToZipUrlMap(upload_path + bizCaseVideo.getVideo_url(), bizCaseVideo.getVideo_name().substring(0, bizCaseVideo.getVideo_name().indexOf(".")), "庭审视频"));
                }

            }
        }

        if (null != bizCaseVideoList2) {
            for (BizCourtRecord bizCourtRecord : bizCaseVideoList2) {
                if (null != bizCourtRecord) {
                    sourceMapList.add(ZipUtils.getToZipUrlMap(upload_path + bizCourtRecord.getRecord_url(), bizCourtRecord.getRecord_name().substring(0, bizCourtRecord.getRecord_name().indexOf(".")), "笔录"));
                }

            }
        }

        if(CollectionUtils.isEmpty(bizCaseVideoList)   && CollectionUtils.isEmpty(bizCaseVideoList2)){
            throw new CustomException("此庭审暂无视频和笔录") ;
        }

        OutputStream sdf = null; // toZip(sourceFiles,sdf);
        try {
            sdf = new FileOutputStream(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipUtils.toZipHTTP(sourceMapList, sdf);
        return uuid + ".zip";
    }
}

package com.jsdc.tsdb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.jsdc.tsdb.core.base.BaseService;
import com.jsdc.tsdb.dao.BizCaseRecordDao;
import com.jsdc.tsdb.mapper.BizCaseInfoMapper;
import com.jsdc.tsdb.mapper.BizCaseRecordMapper;
import com.jsdc.tsdb.mapper.SysUserMapper;
import com.jsdc.tsdb.model.BizCaseInfo;
import com.jsdc.tsdb.model.BizCaseRecord;
import com.jsdc.tsdb.model.SysRoleMenu;
import com.jsdc.tsdb.model.SysUser;
import com.jsdc.tsdb.model.vo.BizCaseRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


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
public class BizCaseRecordService extends BaseService<BizCaseRecordDao, BizCaseRecord> {

    @Autowired
    private BizCaseRecordMapper bizCaseRecordMapper ;

    @Autowired
    private BizCaseInfoMapper bizCaseInfoMapper ;

    @Autowired
    private SysUserMapper sysUserMapper ;



    /**
    *庭审点播日志
    * Author wzn
    * Date 2022/10/11 10:23
    */
    public List<BizCaseRecordVo>  geBizCaseRecordList(BizCaseRecordVo vo){

        //庭审名称、案件编号 模糊查询
        QueryWrapper<BizCaseInfo> queryWrapper1 = new QueryWrapper() ;
        if(StringUtils.hasText(vo.getTrial_name())){
            queryWrapper1.like("trial_name",vo.getTrial_name());
        }
        if(StringUtils.hasText(vo.getCase_code())){
            queryWrapper1.like("case_code",vo.getCase_code());
        }
        List<BizCaseInfo> bizCaseInfos = bizCaseInfoMapper.selectList(queryWrapper1) ;
        ArrayList<String> arrayList = new ArrayList<>() ;
        if(CollectionUtils.isNotEmpty(bizCaseInfos)){
            for(BizCaseInfo s:bizCaseInfos){
                arrayList.add(s.getCase_code()) ;
            }
        }

        //点播人模糊查询
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>() ;
        if(StringUtils.hasText(vo.getOnDemandOf())){
            sysUserQueryWrapper.like("name",vo.getOnDemandOf()) ;
        }
        List<SysUser> sysUsers = sysUserMapper.selectList(sysUserQueryWrapper) ;
        ArrayList<Integer> arrayList2 = new ArrayList<>() ;
        if(CollectionUtils.isNotEmpty(sysUsers)){
            for(SysUser u:sysUsers){
                arrayList2.add(Integer.valueOf(u.getId())) ;
            }
        }

        //查点播记录

        QueryWrapper<BizCaseRecord> queryWrapper = new QueryWrapper() ;
        List<BizCaseRecord> bizCaseRecordList = null ;
        //部分字段翻译
        List<BizCaseRecordVo> bizCaseRecordVoList = new ArrayList<>() ;
        BizCaseRecordVo bizCaseRecordVo = null ;
        if(CollectionUtils.isNotEmpty(arrayList) && CollectionUtils.isNotEmpty(arrayList2)){

            queryWrapper.in("case_code",arrayList) ;
            queryWrapper.in("user_id",arrayList2) ;
            queryWrapper.orderByDesc("view_time") ;
            PageHelper.startPage(vo.getPage(), vo.getLimit());
             bizCaseRecordList = bizCaseRecordMapper.selectList(queryWrapper) ;
            if(CollectionUtils.isNotEmpty(bizCaseRecordList)){
                for(BizCaseRecord b:bizCaseRecordList){
                    bizCaseRecordVo = new BizCaseRecordVo() ;
                    QueryWrapper<BizCaseInfo> infoQueryWrapper = new QueryWrapper<>() ;
                    infoQueryWrapper.eq("case_code",b.getCase_code()) ;
                    BizCaseInfo bizCaseInfo = bizCaseInfoMapper.selectOne(infoQueryWrapper) ;
                    bizCaseRecordVo.setCase_code(b.getCase_code());
                    bizCaseRecordVo.setOnDemandOfTime(b.getView_time());

                    if(null != bizCaseInfo){
                        bizCaseRecordVo.setTrial_name(bizCaseInfo.getTrial_name());
                        bizCaseRecordVo.setTrial_time(bizCaseInfo.getTrial_time());
                        bizCaseRecordVo.setCase_type(bizCaseInfo.getCase_type());
                        bizCaseRecordVo.setUndertake_dept(bizCaseInfo.getUndertake_dept());
                        SysUser sysUser = sysUserMapper.selectById(b.getUser_id()) ;
                        if(null != sysUser){
                            bizCaseRecordVo.setOnDemandOf(sysUser.getName());
                        }
                    }
                    bizCaseRecordVoList.add(bizCaseRecordVo) ;
                }
            }
        }





        return bizCaseRecordVoList ;
    }



    public Integer  getCount(BizCaseRecordVo vo){
        //庭审名称、案件编号 模糊查询
        QueryWrapper<BizCaseInfo> queryWrapper1 = new QueryWrapper() ;
        if(StringUtils.hasText(vo.getTrial_name())){
            queryWrapper1.like("trial_name",vo.getTrial_name());
        }
        if(StringUtils.hasText(vo.getCase_code())){
            queryWrapper1.like("case_code",vo.getCase_code());
        }
        List<BizCaseInfo> bizCaseInfos = bizCaseInfoMapper.selectList(queryWrapper1) ;
        ArrayList<String> arrayList = new ArrayList<>() ;
        if(CollectionUtils.isNotEmpty(bizCaseInfos)){
            for(BizCaseInfo s:bizCaseInfos){
                arrayList.add(s.getCase_code()) ;
            }
        }

        //点播人模糊查询
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>() ;
        sysUserQueryWrapper.like("name",vo.getOnDemandOf()) ;
        List<SysUser> sysUsers = sysUserMapper.selectList(sysUserQueryWrapper) ;
        ArrayList<Integer> arrayList2 = new ArrayList<>() ;
        if(CollectionUtils.isNotEmpty(sysUsers)){
            for(SysUser u:sysUsers){
                arrayList2.add(Integer.valueOf(u.getId())) ;
            }
        }

        //查点播记录

        QueryWrapper<BizCaseRecord> queryWrapper = new QueryWrapper() ;
        queryWrapper.in("case_code",arrayList) ;
        queryWrapper.in("user_id",arrayList2) ;
        queryWrapper.orderByDesc("view_time") ;
        Integer count = bizCaseRecordMapper.selectCount(queryWrapper) ;



        return count ;
    }
}

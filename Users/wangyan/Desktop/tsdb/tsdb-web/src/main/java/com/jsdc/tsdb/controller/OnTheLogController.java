package com.jsdc.tsdb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.model.vo.BizCaseRecordVo;
import com.jsdc.tsdb.service.BizCaseRecordService;
import com.jsdc.tsdb.service.OnTheLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/log")
public class OnTheLogController {

    @Autowired
    private BizCaseRecordService bizCaseRecordService;


    /**
    *5.2.3点播日志
    * Author wzn
    * Date 2022/10/10 11:15
    */
    @PostMapping("/getList")
    private ResultInfo getList(BizCaseRecordVo bizCaseRecordVo){

        List<BizCaseRecordVo> bizCaseRecordVoList = bizCaseRecordService.geBizCaseRecordList(bizCaseRecordVo);
        PageInfo pageInfo = new PageInfo(bizCaseRecordVoList);
        pageInfo.setTotal(bizCaseRecordService.getCount(bizCaseRecordVo));
        return ResultInfo.success(pageInfo);
    }


}

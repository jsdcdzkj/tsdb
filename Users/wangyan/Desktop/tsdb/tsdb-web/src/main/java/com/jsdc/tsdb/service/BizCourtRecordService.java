package com.jsdc.tsdb.service;

import cn.hutool.core.util.IdUtil;
import com.jsdc.tsdb.common.utils.ZipUtils;
import com.jsdc.tsdb.core.base.BaseService;
import com.jsdc.tsdb.dao.BizCourtRecordDao;
import com.jsdc.tsdb.mapper.BizCaseVideoMapper;
import com.jsdc.tsdb.mapper.BizCourtRecordMapper;
import com.jsdc.tsdb.model.BizCaseVideo;
import com.jsdc.tsdb.model.BizCourtRecord;
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
public class BizCourtRecordService extends BaseService<BizCourtRecordDao, BizCourtRecord> {
    @Value("${UPLOAD_PATH}")
    private String upload_path;

    @Value("${dow_path}")
    private String dow_path;

    @Autowired
    private BizCourtRecordMapper bizCourtRecordMapper ;



    /**
     *笔录压缩下载
     * Author wzn
     * Date 2022/10/12 8:36
     */
    public String dowCourt(String[] strings){
        String uuid = IdUtil.simpleUUID() ;
        String name= dow_path+uuid+".zip" ;
        List<Map<String, Object>> sourceMapList = new ArrayList<Map<String, Object>>();
        for(String s:strings){
            BizCourtRecord bizCourtRecord = bizCourtRecordMapper.selectById(s) ;
            if(null != bizCourtRecord){
                sourceMapList.add(ZipUtils.getToZipUrlMap(upload_path+bizCourtRecord.getRecord_url(),bizCourtRecord.getRecord_name().substring(0, bizCourtRecord.getRecord_name().indexOf(".")),"")) ;
            }

        }
        OutputStream sdf = null; // toZip(sourceFiles,sdf);
        try {
            sdf = new FileOutputStream(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipUtils.toZipHTTP(sourceMapList, sdf);
        return uuid+".zip" ;
    }

}

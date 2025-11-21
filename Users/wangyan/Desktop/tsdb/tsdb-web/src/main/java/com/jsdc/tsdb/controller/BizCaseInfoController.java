package com.jsdc.tsdb.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsdc.tsdb.common.Page.Page;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.core.base.BaseController;
import com.jsdc.tsdb.model.*;
import com.jsdc.tsdb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;


/**
 * 庭审案件信息
 */
@Controller
@RequestMapping("/bizCaseInfo")
public class BizCaseInfoController extends BaseController {
    @Autowired
    private BizCaseInfoService bizCaseInfoService;
    @Autowired
    private BizCaseVideoService bizCaseVideoService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private BizCourtRecordService bizCourtRecordService;
    @Autowired
    private SysUserService sysUserService;
    @Value("${UPLOAD_PATH}")
    private String upload_path;


    @RequestMapping(value = "toWorkload.do", method = RequestMethod.GET)
    public String toWorkload(Model model) {
        List<SysDept> sysDepts = sysDeptService.selectList(new QueryWrapper<SysDept>().eq("is_delete", 0));
        model.addAttribute("sysDepts", sysDepts);
        return "/html/baobiao/fgbaobiao/index";
    }

    @RequestMapping(value = "toCaseBf.do", method = RequestMethod.GET)
    public String toCaseBf(Model model) {
        List<SysDept> sysDepts = sysDeptService.selectList(new QueryWrapper<SysDept>().eq("is_delete", 0));
        model.addAttribute("sysDepts", sysDepts);
        return "/html/tsspgl/tsspbf/index";
    }

    @RequestMapping(value = "toCaseDb.do", method = RequestMethod.GET)
    public String toCaseDb(Model model) {
//        List<SysDept> sysDepts = sysDeptService.selectList(new QueryWrapper<SysDept>().eq("is_delete", 0));
//        model.addAttribute("sysDepts", sysDepts);
        return "/html/tsspgl/tsspdb/index";
    }

    @RequestMapping(value = "toCaseDbDetail.do", method = RequestMethod.GET)
    public String toCaseDbDetail(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/html/tsspgl/tsspdb/detail";
    }

    /**
     * 查询总体量统计
     *
     * @param bizCaseInfo
     * @return
     */
    @RequestMapping(value = "getTotal.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getTotal(@RequestBody BizCaseInfo bizCaseInfo) {
        return ResultInfo.success(bizCaseInfoService.getTotal(bizCaseInfo));
    }

    /**
     * 查询案件类型占比
     *
     * @param bizCaseInfo
     * @return
     */
    @RequestMapping(value = "getTypeRatio.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getTypeRatio(@RequestBody BizCaseInfo bizCaseInfo) {
        return ResultInfo.success(bizCaseInfoService.getTypeRatio(bizCaseInfo));
    }

    /**
     * 查询承办部门占比
     *
     * @param bizCaseInfo
     * @return
     */
    @RequestMapping(value = "getDeptRatio.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getDeptRatio(@RequestBody BizCaseInfo bizCaseInfo) {
        return ResultInfo.success(bizCaseInfoService.getDeptRatio(bizCaseInfo));
    }

    /**
     * 查询法官工作量统计
     *
     * @param bizCaseInfo
     * @return
     */
    @RequestMapping(value = "getWorkload.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getWorkload(@RequestBody BizCaseInfo bizCaseInfo) {
        return ResultInfo.success(new PageInfo(bizCaseInfoService.getWorkload(bizCaseInfo)));
    }

    /**
     * 查询庭审案件信息列表
     *
     * @param bizCaseInfo
     * @return
     */
    @RequestMapping(value = "getCaseInfosBackup.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getCaseInfosBackup(@RequestBody BizCaseInfo bizCaseInfo) {
        PageHelper.startPage(bizCaseInfo.getPage(), bizCaseInfo.getLimit());
        QueryWrapper qw = new QueryWrapper();
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_code())) {
            qw.eq("case_code", bizCaseInfo.getCase_code());
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_name())) {
            qw.like("case_name", bizCaseInfo.getCase_name());
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_type())) {
            qw.eq("case_type", bizCaseInfo.getCase_type());
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getUndertake_dept())) {
            qw.eq("undertake_dept", bizCaseInfo.getUndertake_dept());
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getTrial_time())) {
            qw.between("trial_time", bizCaseInfo.getTrial_time() + " 00:00:00", bizCaseInfo.getTrial_time() + " 23:59:59");
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_time())) {
            qw.between("case_time", bizCaseInfo.getCase_time() + " 00:00:00", bizCaseInfo.getCase_time() + " 23:59:59");
        }
        qw.orderByDesc("trial_time", "case_time");
        List<BizCaseInfo> bizCaseInfoList = bizCaseInfoService.selectList(qw);
        PageInfo pageInfo = new PageInfo(bizCaseInfoList);
        return ResultInfo.success(pageInfo);
    }

    /**
     * 查询案件详情信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getCaseDetail.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getCaseDetail(Integer id) {
        JSONObject data = new JSONObject();
        BizCaseInfo bizCaseInfo = bizCaseInfoService.selectById(id);
        List<BizCaseVideo> bizCaseVideos = bizCaseVideoService.selectList(new QueryWrapper<BizCaseVideo>().eq("case_code", bizCaseInfo.getCase_code()));
        List<BizCourtRecord> bizCourtRecords = bizCourtRecordService.selectList(new QueryWrapper<BizCourtRecord>().eq("case_code", bizCaseInfo.getCase_code()));
        data.put("bizCaseInfo", bizCaseInfo);
        //视频
        data.put("bizCaseVideos", bizCaseVideos);
        //笔录
        data.put("bizCourtRecords", bizCourtRecords);
        //视频服务地址
        data.put("upload_path", upload_path);
        return ResultInfo.success(data);
    }

    /**
     * 是否公开修改
     *
     * @return
     */
    @RequestMapping(value = "editPub.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo editPub(Integer id, String is_pub) {
        BizCaseInfo bizCaseInfo = bizCaseInfoService.selectById(id);
        bizCaseInfo.setIs_pub(is_pub);
        bizCaseInfo.updateById();
        return ResultInfo.success();
    }

    /**
     * 获取查询案件点播列表
     *
     * @param bizCaseInfo
     * @return
     */
    @RequestMapping(value = "getCaseInfosDb.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getCaseInfos(@RequestBody BizCaseInfo bizCaseInfo) {
        SysUser sysUser = sysUserService.getUser();
        List<BizCaseInfo> bizCaseInfos = bizCaseInfoService.getCaseInfos(bizCaseInfo,sysUser);
        PageInfo pageInfo = new PageInfo(bizCaseInfos);
        return ResultInfo.success(pageInfo);
    }
    /**
     * 记录用户点播视频记录
     *
     * @param case_code
     * @return
     */
    @RequestMapping(value = "genUserDbRecord.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo genUserDbRecord(String case_code){
        SysUser sysUser = sysUserService.getUser();
        BizCaseRecord bizCaseRecord = new BizCaseRecord();
        bizCaseRecord.setCase_code(case_code);
        bizCaseRecord.setUser_id(sysUser.getId());
        bizCaseRecord.setView_time(DateUtil.formatDateTime(new Date()));
        bizCaseRecord.insert();
        return ResultInfo.success();
    }

    /**
     * 笔录word转pdf在线预览
     *
     * @param response
     */
    @RequestMapping("/showPdf")
    public void testPdf(String recordUrl, String fileName, HttpServletResponse response) {
        response.setContentType("application/pdf; charset=utf-8");
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + "xxx.pdf" + "\"");
        String[] strs = fileName.split("\\.");
        response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncodeUtil.encode(strs[0], Charset.forName("utf-8")) + ".pdf\"");
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
        }
        InputStream is = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            String encode = URLEncodeUtil.encode(upload_path + recordUrl, Charset.forName("utf-8"));
            URL url = new URL(encode);
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            if (httpconn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {
                is = httpconn.getInputStream();
            }
            Document doc1 = new Document(is); // Address是将要被转化的word文档
//            Document doc2 = new Document("G:\\db_resource\\AJ00012_2022-10-08\\笔录\\笔录001.docx"); // Address是将要被转化的word文档
            doc1.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * pdf水印
     *
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }





    /**
    *庭审视频压缩下载
    * Author wzn
    * Date 2022/10/12 8:39
    */
    @RequestMapping(value = "dowVideo.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo dowVideo(String[] strings) {
        String name = bizCaseVideoService.dowVideo(strings);
        return ResultInfo.success(name);
    }


    /**
    *庭审笔录压缩下载
    * Author wzn
    * Date 2022/10/12 8:39
    */
    @RequestMapping(value = "dowCourt.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo dowCourt(String[] strings) {
        String name = bizCourtRecordService.dowCourt(strings);
        return ResultInfo.success(name);
    }



    /**
    *视频/笔录下载
    * Author wzn
    * Date 2022/10/12 17:06
    */
    @RequestMapping(value = "dowVideoAndCount.do", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo dowVideoAndCount(String caseCode) {
        String name = bizCaseInfoService.dowVideoAndCount(caseCode);
        return ResultInfo.success(name);
    }

}

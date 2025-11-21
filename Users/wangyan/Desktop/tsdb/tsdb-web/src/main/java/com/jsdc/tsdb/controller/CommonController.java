package com.jsdc.tsdb.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsdc.tsdb.common.utils.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    @Value("${dow_path}")
    private String dow_path;



   /**
   *通用下载接口
   * Author wzn
   * Date 2022/10/11 17:18
   */
    @GetMapping("common/temdownload")
    public void temresourceDownload(String fileName, Boolean delete, HttpServletRequest request, HttpServletResponse response,String name)
            throws Exception {

        // 文件路径
        String downloadPath = dow_path +fileName;
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, name));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
        if (delete) {
            FileUtils.deleteFile(downloadPath);
        }
    }

   
}

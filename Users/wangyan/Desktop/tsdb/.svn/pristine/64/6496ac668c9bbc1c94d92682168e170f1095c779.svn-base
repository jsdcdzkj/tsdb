package com.jsdc.tsdb.controller;

import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsdc.tsdb.common.upload.FileRepository;
import com.jsdc.tsdb.common.utils.GlobalData;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.common.utils.Utils;
import com.jsdc.tsdb.core.base.BaseController;
import com.jsdc.tsdb.model.SysMenu;
import com.jsdc.tsdb.model.SysUser;
import com.jsdc.tsdb.service.SysMenuService;
import com.jsdc.tsdb.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController extends BaseController {

    @Autowired
    private SysUserService userService;
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private SysMenuService sysMenuService ;

    @RequestMapping(value = "login.do", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    public List<SysUser> getdata(Integer id, SysUser sysUser) {
        List<SysUser> sysUsers = userService.selectList(new QueryWrapper<>());
        return sysUsers;
    }

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public String loginPost(String consumer, String cypher) {
        JSONObject jsonObject = new JSONObject();
        if (empty(consumer)) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "用户名不能为空");
        }
        if (empty(cypher)) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "密码不能为空");
        }
        if (notEmpty(consumer) && notEmpty(cypher)) {
            ResultInfo resultInfo = userService.login(consumer, cypher);

            if (resultInfo.getCode() == 0) {
                SysUser sysUser = (SysUser) resultInfo.getData();
                if (GlobalData.ISENABLE_YES.equals(sysUser.getIs_enable())) {
                    jsonObject.put("success", true);
                    jsonObject.put("msg", "登录成功");
                }else{
                    jsonObject.put("success", false);
                    jsonObject.put("msg", "账号被禁用，请联系管理员");
                }

            } else {
                jsonObject.put("success", false);
                jsonObject.put("msg", "用户名或密码错误");
            }
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:main.do";
    }

    @RequestMapping(value = "main.do")
    public String main(Model model) {
        model.addAttribute("list",sysMenuService.getMenuList()) ;
        model.addAttribute("sysUser",userService.getUser()) ;
        return "index";
    }


    @RequestMapping(value = "top.do")
    public String top() {
        return "top";
    }

    @RequestMapping(value = "left.do")
    public String left() {
        return "left";
    }

    @RequestMapping(value = "open.do", method = RequestMethod.GET)
    public String open() {
        return "open";
    }

    @RequestMapping(value = "upLoadFile.json", method = RequestMethod.POST)
    @ResponseBody
    public String upLoadPhoto(MultipartFile file, HttpServletResponse response) {
        String origName1 = "";
        if (file.getSize() > 0) {
            try {
                if (file != null) {
                    origName1 = file.getOriginalFilename();
                    String filename = "/" + origName1;
                    String s = fileRepository.storePhotoByExt(file, filename);
                    Utils.docToPdf(s, s.substring(0, s.lastIndexOf(".")) + ".pdf");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", origName1.substring(0, origName1.lastIndexOf(".")) + ".pdf");
        return jsonObject.toJSONString();
    }



    //url资源转化为file流
    public static File urlToFile(String urlPath, String filePath) {
        int HttpResult; // 服务器返回的状态
        InputStream is = null;
        File file = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL(urlPath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            if (HttpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {
//                file = File.createTempFile("tmp", null);
                file = new File(filePath);
                URLConnection urlConn = null;
                urlConn = url.openConnection();
                is = urlConn.getInputStream();
                fos = new FileOutputStream(file);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
        return file;
    }
}

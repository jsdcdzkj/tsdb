package com.jsdc.tsdb.common.task;




import cn.hutool.http.webservice.SoapClient;
import com.jsdc.tsdb.model.vo.CaseVo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.*;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class synchronus {
    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        //todo 调用2.1案件批量下载接口

//        //先获取总页数  如果大于1多次请求
//        Integer count = getPage();
//        if(count == 1){
//            String xml = send("1") ;
//            insertData(xml) ;
//        }else{
//            for(int i = 0 ; i<count ; i++){
//                String xml = send(String.valueOf(i+1)) ;
//                insertData(xml) ;
//            }
//        }






    }


    /**
    *发送请求  返回xml字符串
    * Author wzn
    * Date 2022/10/14 16:01
    */
    public  String send(String page){

        Map<String, Object> params = new HashMap<>();

        // 新建客户端
        SoapClient client = SoapClient.create("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?WSDL")
                // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
                .setMethod("web:GetPlAj", "http://WebXml.com.cn/")
                // 设置参数，此处自动添加方法的前缀：web
                .setParam("fydm", "法院代码")
                .setParam("token", "访问通行证")
                .setParam("xml ", "请求xml");

        // 发送请求，参数true表示返回一个格式化后的XML内容
        // 返回内容为XML字符串，可以配合XmlUtil解析这个响应
        String xml = client.send(true);
        return xml ;
    }


    /**
    *解析xml数据
    * Author wzn
    * Date 2022/10/14 16:01
    */
    public void insertData(String xml){
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element roots = doc.getRootElement();
            ;
            Iterator elements = roots.elementIterator();
            List<CaseVo> caseVoList = new ArrayList<>() ;
            CaseVo caseVo = null ;
            boolean isCode = false ;
            String msg = "" ;
            while (elements.hasNext()) {
                Element child = (Element) elements.next();
                Iterator<?> iterator2 = child.elementIterator();
                while(iterator2.hasNext()) {
                    caseVo = new CaseVo() ;
                    Element element2 = (Element)iterator2.next();

                    if("Code".equals(element2.getName())){
                        caseVo.setCode(element2.getText());
                        if("0".equals(element2.getText())){
                            isCode = true ;
                        }

                    }else if("Msg".equals(element2.getName())){
                        caseVo.setMsg(element2.getText());
                        if(!"".equals(element2.getText())){
                            System.out.println(element2.getText());
                        }
                    }
                    if(isCode){
                        Iterator<?> iterator3 = element2.elementIterator();
                        while(iterator3.hasNext()) {

                            Element element3 = (Element)iterator3.next();
                            if("AJMC".equals(element3.getName())){
                                caseVo.setAJMC(element3.getText());
                            }else if("AJLB".equals(element3.getName())){
                                caseVo.setAJLB(element3.getText());
                            }
                        }
                        if(!"".equals(caseVo.getAJMC()) && null != caseVo.getAJMC()){
                            caseVoList.add(caseVo) ;
                        }
                    }

                }


            }
            System.out.println(caseVoList);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public Integer getPage(){
        Integer count = 1 ;
        //首次默认第一页
        String xml2 = send("1") ;
        try {
            Document doc = DocumentHelper.parseText(xml2);
            Element roots = doc.getRootElement();
            ;
            Iterator elements = roots.elementIterator();
            List<CaseVo> caseVoList = new ArrayList<>() ;
            CaseVo caseVo = null ;
            boolean isCode = false ;
            String msg = "" ;
            while (elements.hasNext()) {

                Element child = (Element) elements.next();
                System.out.println();
                if ("Data".equals(child.getName())) {
                    //总页数
                    count = Integer.valueOf(child.attributeValue("TotalpageNum"));

                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return count ;
    }
}
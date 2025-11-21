package com.jsdc.tsdb.common.utils;

import com.jsdc.tsdb.core.common.handler.PageHandler;
import lombok.Data;

/**
 * 统一返回类
 * @param <T>
 */
@Data
public class ResultInfo<T> {
    private Integer code;
    private String msg;
    private T data;
    private String logMsg;
    private int count;
    private int index;
    private int size;
    /**
     *  成功
     * @return
     */
    public static ResultInfo success(){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(0);
        resultInfo.setMsg("成功");
        return resultInfo;
    }

    /**
     * 成功返回
     * @param o 返回对象
     * @return
     */
    public static ResultInfo success(Object o){
        ResultInfo resultInfo = new ResultInfo();
        if(o instanceof PageHandler){
            PageHandler pageHandler = (PageHandler) o;
            resultInfo.setCount(pageHandler.getTotalCount());
            resultInfo.setData(pageHandler.getList());
            resultInfo.setIndex(pageHandler.getPageIndex());
            resultInfo.setSize(pageHandler.getPageSize());
            resultInfo.setMsg("success");
            resultInfo.setCode(0);
            return resultInfo;
        }else{
            resultInfo.setCode(0);
            resultInfo.setMsg("成功");
            resultInfo.setData(o);
            return resultInfo;
        }
    }
    /**
     * 成功返回
     * @param o 返回对象
     * @return
     */
    public static ResultInfo success(Object o,String logMsg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(0);
        resultInfo.setMsg("成功");
        resultInfo.setData(o);
        resultInfo.setLogMsg(logMsg);
        return resultInfo;
    }
    /**
     * 失败返回
     * @param msg 错误信息
     * @return
     */
    public static ResultInfo error(String msg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(-1);
        resultInfo.setMsg(msg);
        return resultInfo;
    }
    /**
     * 失败返回
     * @param msg 错误信息
     * @return
     */
    public static ResultInfo error(String msg,String logMsg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(-1);
        resultInfo.setMsg(msg);
        resultInfo.setLogMsg(logMsg);
        return resultInfo;
    }

    /**
     * 自定义返回结果
     * @param code 状态码
     * @param msg  返回信息
     * @param o 返回对象
     * @return
     */
    public static ResultInfo customize_response(Integer code,String msg,Object o,String logMsg){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMsg(msg);
        resultInfo.setData(o);
        resultInfo.setLogMsg(logMsg);
        return resultInfo;
    }


}

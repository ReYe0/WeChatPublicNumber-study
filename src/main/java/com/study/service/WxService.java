package com.study.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WxService {
    /**
     * 微信接入申请
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return java.lang.String
     * @author xuy
     * @date 2023/1/14 15:13
     */
    void joinUp(HttpServletRequest req,HttpServletResponse resp);
}

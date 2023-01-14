package com.study.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WxService {

    /**
     * T微信接入申请
     * @param req
     * @param resp
     * @return void
     * @author xuy
     * @date 2023/1/14 16:48
     */
    void joinUp(HttpServletRequest req,HttpServletResponse resp);
}

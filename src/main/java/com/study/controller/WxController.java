package com.study.controller;

import com.study.entity.WxJoinUP;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WxController  {

//    @RequestParam(name = "signature", required = false) String signature,
//    @RequestParam(name = "timestamp", required = false) String timestamp,
//    @RequestParam(name = "nonce", required = false) String nonce,
//    @RequestParam(name = "echostr", required = false) String echostr
    @RequestMapping(value = "/api",name = "微信公众号基本配置接入api,用户发送的消息也可以收",produces="application/json")
    void joinUp(HttpServletRequest req, HttpServletResponse resp);

//    @RequestMapping(value = "/test",name = "测试为何@RequestBody失效,猜测应该是返回的参数有多个",produces="application/json")
//    String test(@RequestBody WxJoinUP wxJoinUP);

    @RequestMapping(value = "/receiveUserMessage",name = "接收用户发送的消息",produces="application/json")
    void receiveUserMessage( HttpServletRequest req);

}

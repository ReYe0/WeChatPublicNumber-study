package com.study.controller.Impl;

import com.study.controller.WxController;
import com.study.entity.WxJoinUP;
import com.study.service.WxService;
import com.study.util.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxControllerImpl implements WxController {
    @Autowired
    private WxService wxService;

    @Override
    public void  joinUp(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("请求到达了");
        wxService.joinUp(req,resp);

        Map<String,String> map = null;
        try {
            //设置编码格式,不然中文会乱码
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            map = WxUtils.parseRequest(req.getInputStream());
            System.out.println("用户发送的数据："+map);//关注测试号,给测试公众号发消息,就可以看到打印结果了
            //回复消息
            String textMsg = "<xml><ToUserName><![CDATA["+map.get("FromUserName")+"]]></ToUserName><FromUserName><![CDATA["+map.get("ToUserName")+"]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";
            resp.getWriter().print(textMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveUserMessage(HttpServletRequest req) {
        Map<String,String> map = null;
        try {
            map = WxUtils.parseRequest(req.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("用户发送的数据："+map);//关注测试号,给测试公众号发消息,就可以看到打印结果了
    }


//    @Override
//    public String  test(WxJoinUP wxJoinUP) {
//        System.out.println("请求到达了");
//        //取出微信服务器传过来的参数
//        String signature = wxJoinUP.getSignature();
//        String timestamp = wxJoinUP.getTimestamp();
//        String nonce = wxJoinUP.getNonce();
//        String echostr = wxJoinUP.getEchostr();
//        //自定义一个check方法用来校验接入
//        boolean success = WxUtils.check(timestamp, nonce, signature);
//        if(success){
//            System.out.println("接入成功");
//            return echostr;
//        }else{
//            System.out.println("接入失败");
//        }
//        return signature;
//    }
}

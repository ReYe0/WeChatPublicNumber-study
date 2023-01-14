package com.study.service.Impl;

import com.study.service.WxService;
import com.study.util.WxUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class WxServiceImpl implements WxService {
    @Override
    public void joinUp(HttpServletRequest req,HttpServletResponse resp) {
        //取出微信服务器传过来的参数
        String signature = req.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
        String timestamp = req.getParameter("timestamp");//时间戳
        String nonce = req.getParameter("nonce");//随机数
        String echostr = req.getParameter("echostr");//随机字符串
        //自定义一个check方法用来校验接入
        boolean success = WxUtils.check(timestamp, nonce, signature);
        if(success){
            System.out.println("接入成功");
            PrintWriter writer = null;
            try {
                writer = resp.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.write(echostr);//接入成功需要原样返回echostr
//            return echostr;
        }else{
            System.out.println("接入失败");
        }
//        return signature;
    }
}

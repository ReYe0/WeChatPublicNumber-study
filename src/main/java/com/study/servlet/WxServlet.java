package com.study.servlet;

import com.study.service.WxService;
import com.study.util.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/servlet")
public class WxServlet extends HttpServlet {

    @Autowired
    private WxService wxService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("请求到达了");
        wxService.joinUp(req,resp);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式,不然中文会乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //将请求中的xml参数转成map
        Map<String,String> map = WxUtils.parseRequest(req.getInputStream());
        System.out.println("用户发送的消息的详细信息:"+map);//关注测试号,给测试公众号发消息,就可以看到打印结果了
        //回复消息，xml格式如下，替换下面的两个参数
        String textMsg = "<xml>\n" +
                "  <ToUserName><![CDATA["+map.get("FromUserName")+ "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+map.get("ToUserName")+ "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[你好]]></Content>\n" +
                "</xml>";
        String respXml = WxUtils.getResponse(map);
        System.out.println("respXml:"+ respXml);
//        resp.getWriter().print(textMsg);//基础用法
        resp.getWriter().print(respXml);
    }
}

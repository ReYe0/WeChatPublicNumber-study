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
        Map<String,String> map = WxUtils.parseRequest(req.getInputStream());
        System.out.println(map);//关注测试号,给测试公众号发消息,就可以看到打印结果了
    }
}

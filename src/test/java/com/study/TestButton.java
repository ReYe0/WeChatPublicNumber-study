package com.study;

import java.util.ArrayList;
import java.util.List;

import com.study.entity.*;
import com.study.service.WxService;
import com.study.util.HttpUtils;
import com.study.util.WxUtils;
import org.junit.Test;
import com.alibaba.fastjson.JSONObject;

public class TestButton {

    @Test
    public void setButton() {
        //创建一级菜单
        Button button = new Button();
        //在第三个菜单中创建二级菜单
        SubButton subButton = new SubButton("有子菜单");
        List<AbstractButton> list2 = new ArrayList();
        list2.add(new ClickButton("三一点击", "31"));
        list2.add(new ViewButton("码云博客", "https://heliufang.gitee.io/"));
        list2.add(new PhotoOrAlbumButton("拍照或发图","32"));
        subButton.setSub_button(list2);
        //在一级菜单中添加三个按钮,
        List<AbstractButton> list = new ArrayList();
        list.add(new ClickButton("一级点击", "1"));
        list.add(new ViewButton("个人博客", "https://heliufang.gitee.io/"));
        list.add(subButton);
        button.setButton(list);
        //转成json格式字符串
        String jsonString = JSONObject.toJSONString(button);
        //System.out.println(jsonString);
        //发送请求
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxUtils.getAccessToken());//把token带上
        String result = HttpUtils.sendPost(url, jsonString);
        System.out.println(result);
    }
}
package com.study;

import com.study.util.HttpUtils;
import com.study.util.WxUtils;
import org.junit.Test;

public class TestGetUserInfo {
    @Test
    public void getUserInfo() {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN", WxUtils.getAccessToken());
        url = url.replace("OPENID", "oV0Zw5xgiky-bW8wA9Z6GdisTKts");
        String string = HttpUtils.sendGet(url);
        System.out.println(string);//这里就可以看到打印的用户信息了
    }
}

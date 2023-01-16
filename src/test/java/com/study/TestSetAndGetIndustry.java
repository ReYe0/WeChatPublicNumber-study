package com.study;

import com.study.util.HttpUtils;
import com.study.util.WxUtils;
import org.junit.Test;

public class TestSetAndGetIndustry {
    @Test
    public void setIndustry() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxUtils.getAccessToken());
        String jsonStr = "{\"industry_id1\":\"1\",\"industry_id2\":\"4\"}";
        String rString = HttpUtils.sendPost(url, jsonStr);
        System.out.println(rString);
    }

    @Test
    public void getIndustry() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxUtils.getAccessToken());
        String string = HttpUtils.sendGet(url);
        System.out.println(string);
    }
}

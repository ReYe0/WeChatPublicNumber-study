package com.study;

import com.study.util.HttpUtils;
import com.study.util.WxUtils;
import org.junit.Test;

public class TestTemporaryMaterial {
    //上传图片
    @Test
    public void uploadMedia() {
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        url = url.replace("ACCESS_TOKEN", WxUtils.getAccessToken());
        url = url.replace("TYPE", "image");
        String string = HttpUtils.sendPost(url, null, "E:\\1.jpeg", "");
        System.out.println(string);
    }

    //获取上传的图片
    @Test
    public void getMedia() {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        url = url.replace("ACCESS_TOKEN", WxUtils.getAccessToken());
        url = url.replace("MEDIA_ID", "nHozoYXc7hPM7GYZ4fFwoJXo8sFdW4vKvfV3vSNys6PlWm_61vUWybaVw4ikQ35X");
        String string = HttpUtils.sendGet(url);
        System.out.println(string);
    }
}

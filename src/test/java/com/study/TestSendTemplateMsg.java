package com.study;

import com.study.util.HttpUtils;
import com.study.util.WxUtils;
import org.junit.Test;

public class TestSendTemplateMsg {
    @Test
    public void sendTemplateMsg() {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxUtils.getAccessToken());
        //实际开发中应封装成java类,再把java对象转成类似下面的jsonstr
        String jsonStr = "{\r\n" +
                "	\"touser\": \"oV0Zw5xgiky-bW8wA9Z6GdisTKts\",\r\n" +
                "	\"template_id\": \"rELphAI1QKHWN0oa-6cT7uFF05XMsip66CtoggIIFJo\",\r\n" +
                "	\"url\": \"https://heliufang.gitee.io/\",\r\n" +
                "	\"data\": {\r\n" +
                "		\"first\": {\r\n" +
                "			\"value\": \"您好!您投递的简历有新的反馈\",\r\n" +
                "			\"color\": \"#173177\"\r\n" +
                "		},\r\n" +
                "		\"company\": {\r\n" +
                "			\"value\": \"广州壹新网络科技有限公司\",\r\n" +
                "			\"color\": \"#173177\"\r\n" +
                "		},\r\n" +
                "		\"time\": {\r\n" +
                "			\"value\": \"2021-8-5 23:31:23\",\r\n" +
                "			\"color\": \"#173177\"\r\n" +
                "		},\r\n" +
                "		\"result\": {\r\n" +
                "			\"value\": \"已通过\",\r\n" +
                "			\"color\": \"#ff0000\"\r\n" +
                "		},\r\n" +
                "		\"remark\": {\r\n" +
                "			\"value\": \"带身份证\",\r\n" +
                "			\"color\": \"#173177\"\r\n" +
                "		}\r\n" +
                "	}\r\n" +
                "}";
        String rString = HttpUtils.sendPost(url, jsonStr);
        System.out.println(rString);
    }
}

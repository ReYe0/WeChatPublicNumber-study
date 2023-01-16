package com.study.util;

import com.alibaba.fastjson.JSONObject;
import com.study.entity.*;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.*;

public class WxUtils {

    public static final String TOKEN = "xuy";//在微信配置界面自定义的token
    private static AccessToken at;//token获取的次数有限,有效期也有限,所以需要保存起来
    private static String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //登录测试号管理界面-测试号信息下面可以得到你的APPID和APPSECRET
    private static String APPID = "wxf9322665468f1b86";
    private static String APPSECRET = "bcac3e66536177e5d4b1bfc532733d63";

    /**
     * 接入校验
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public static boolean check(String timestamp, String nonce, String signature) {
        //1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(arr);
        //2.将三个参数字符串拼接成一个字符串进行sha1加密  https://www.cnblogs.com/2333/p/6405386.html
        String str = arr[0]+arr[1]+arr[2];
        str = DigestUtils.sha1Hex(str);//sha1加密,这里没有像罗老师那样手写,直接用的commons-codec包的工具类
        System.out.println("str:"+str);
        //3.将加密后的字符串和signature比较
        System.out.println(signature);
        return str.equalsIgnoreCase(signature);
    }

    /**
     * 将接受到的消息转化成map
     * @param is
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author xuy
     * @date 2023/1/14 15:25
     */
    public static Map<String, String> parseRequest(InputStream is) {
        Map<String, String> map = new HashMap<String, String>();
        // 1.通过io流得到文档对象
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 2.通过文档对象得到根节点对象
        Element root = document.getRootElement();
        // 3.通过根节点对象获取所有子节点对象
        List<Element> elements = root.elements();
        // 4.将所有节点放入map
        for (Element element : elements) {
            map.put(element.getName(), element.getStringValue());
        }
        return map;
    }

    /**
     * 事件消息回复
     * @param requestMap
     * @return java.lang.String
     * @author xuy
     * @date 2023/1/14 17:12
     */
    public static String getResponse(Map<String, String> requestMap) {
        BaseMsg msg = null;
        // 根据用户发送消息的类型,做不同的处理
        String msgType = requestMap.get("MsgType");
        switch (msgType) {
            case "text":
                msg = dealTextMsg(requestMap);
                break;
            case "news":
                break;
            default:
                break;
        }
        // System.out.println(msg);
        // 将处理结果转化成xml的字符串返回
        if (null != msg) {
            return beanToXml(msg);
        }
        return null;
    }

    /**
     * 将回复的消息类转成xml字符串
     * @param msg
     * @return java.lang.String
     * @author xuy
     * @date 2023/1/14 17:11
     */
    public static String beanToXml(BaseMsg msg) {
        XStream stream = new XStream();
        stream.processAnnotations(TextMsg.class);
        stream.processAnnotations(NewsMsg.class);
        String xml = stream.toXML(msg);
        return xml;
    }
    /**
     * 当用户发送是文本消息的处理逻辑
     * @param requestMap
     * @return com.study.entity.BaseMsg
     * @author xuy
     * @date 2023/1/14 17:11
     */
    private static BaseMsg dealTextMsg(Map<String, String> requestMap) {
        // 获取用户发送的消息内容
        String msg = requestMap.get("Content");
        // 如果是图文回复一个图文消息
        if (msg.equals("图文")) {
            List<Article> articles = new ArrayList<Article>();
            articles.add(new Article("码云博客", "这个是我个人的码云博客,基于hexo搭建,里面的文章都是使用markdown编写",
                    "https://heliufang.gitee.io/uploads/banner.jpg", "https://heliufang.gitee.io/"));
            return new NewsMsg(requestMap, articles);
        }
        //否则回复一个文本消息,文本内容为'当前时间+你好'
        //当然这个内容可以自定义,在这里也可以接入自动回复机器人
        TextMsg textMsg = new TextMsg(requestMap, new Date(System.currentTimeMillis()).toLocaleString() + "你好");
        return textMsg;
    }


    /**
     * 发送get请求获取AccessToken
     */
    private static void getToken() {
        String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        String tokenStr = HttpUtils.sendGet(url);//调用工具类发get请求
        System.out.println(tokenStr);
        JSONObject jsonObject = JSONObject.parseObject(tokenStr);
        String token = jsonObject.getString("access_token");
        String expiresIn = jsonObject.getString("expires_in");
        at = new AccessToken(token, expiresIn);
    }

    /**
     * 获取AccessToken  向外提供
     */
    public static String getAccessToken() {
        //过期了或者没有值再去发送请求获取
        if(at == null || at.isExpire()) {
            getToken();
        }
        return at.getToken();
    }
}

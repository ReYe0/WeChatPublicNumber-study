package com.study.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxUtils {

    public static final String TOKEN = "xuy";//在微信配置界面自定义的token

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
}

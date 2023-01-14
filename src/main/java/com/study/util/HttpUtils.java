package com.study.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
/**
 * AccessToken 封装Access token请求工具类
 */
public class HttpUtils {

    public static void main(String[] args) {
        // 1.测试get请求
		/*
		 String getUrl = "http://localhost:8080/user/searchPage?pageNum=1&pageSize=2";
		 System.out.println(sendGet(getUrl));
		 */

        // 2.测试post请求 携带x-www-form-urlencoded数据格式
		/*String postUrlForm = "http://localhost:8080/user";
		Map paramMap = new HashMap();
		paramMap.put("name", "杰克");
		paramMap.put("age", "20");
		paramMap.put("gender", "1");
		System.out.println(sendPost(postUrlForm, paramMap));*/

        //3.测试post请求 携带json数据格式
		/*String postUrlJson = "http://localhost:8080/user";
		String jsonParam = "{\"name\":\"jack\",\"age\":\"18\",\"gender\":\"2\"}";
		System.out.println(sendPost(postUrlJson,jsonParam));*/

        //4 测试post 携带文件
        String postUrlFile = "http://localhost:8080/user/upload";
        Map paramMap = new HashMap();
        paramMap.put("name", "tom");
        String localFile = "d:\\logo.png";
        String fileParamName = "file";
        System.out.println(sendPost(postUrlFile, paramMap,localFile,fileParamName));
    }

    // 1.httpClient发送get请求
    public static String sendGet(String url) {
        String result = "";
        CloseableHttpResponse response = null;
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(url);// 这里发送get请求
            // 获取当前客户端对象
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 通过请求对象获取响应对象
            response = httpClient.execute(request);
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // 2.httpClient发送post请求 携带x-www-form-urlencoded数据格式
    public static String sendPost(String url, Map<String, String> map) {
        CloseableHttpResponse httpResponse = null;
        String result = "";
        try {
            // 1、创建一个httpClient客户端对象
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 2、创建一个HttpPost请求
            HttpPost httpPost = new HttpPost(url);
            // 设置请求头
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded"); // 设置传输的数据格式
            // 携带普通的参数params的方式
            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            Set<String> keys = map.keySet();
            for (String key : keys) {
                params.add(new BasicNameValuePair(key, map.get(key)));
            }
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
            // 这里就是：username=kylin&password=123456
            System.out.println(str);

            // 放参数进post请求里面 从名字可以知道 这个类是专门处理x-www-form-urlencoded 添加参数的
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            // 7、执行post请求操作，并拿到结果
            httpResponse = httpClient.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            } else {
                EntityUtils.consume(entity);//// 如果entity为空，那么直接消化掉即可
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // 3.httpClient发送post请求 携带json数据格式
    public static String sendPost(String url, String jsonStr) {
        CloseableHttpResponse httpResponse = null;
        String result = "";
        try {
            // 1.创建httpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 2.创建post请求方式实例
            HttpPost httpPost = new HttpPost(url);

            // 2.1设置请求头 发送的是json数据格式
            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader("Connection", "Close");

            // 3.设置参数---设置消息实体 也就是携带的数据
            /*
             * 比如传递： { "username": "aries", "password": "666666" }
             */
            //String jsonStr = " {\"username\":\"aries\",\"password\":\"666666\"}";
            StringEntity entity = new StringEntity(jsonStr.toString(), Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8"); // 设置编码格式
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            // 把请求消息实体塞进去
            httpPost.setEntity(entity);

            // 4.执行http的post请求
            // 4.执行post请求操作，并拿到结果
            httpResponse = httpClient.execute(httpPost);
            // 获取结果实体
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity, "UTF-8");
            } else {
                EntityUtils.consume(httpEntity);//// 如果httpEntity为空，那么直接消化掉即可
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // 4.httpClient发送post请求 携带文件
    public static String sendPost(String url, Map<String, String> map,String localFile, String fileParamName) {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 把文件转换成流对象FileBody
            FileBody bin = new FileBody(new File(localFile));

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            // 相当于<input type="file" name="fileParamName"/> 其中fileParamName以传进来的为准
            builder.addPart(fileParamName, bin);
            // 相当于<input type="text" name="userName" value=userName>
            /*builder.addPart("filesFileName",
                    new StringBody(fileParamName, ContentType.create("text/plain", Consts.UTF_8)));*/
            if (map != null) {
                for (String key : map.keySet()) {
                    builder.addPart(key,
                            new StringBody(map.get(key), ContentType.create("text/plain", Consts.UTF_8)));
                }
            }
            HttpEntity reqEntity = builder.build();
            httpPost.setEntity(reqEntity);
            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost, HttpClientContext.create());
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
package com.study.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 基础消息类的封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseMsg {
    @XStreamAlias("ToUserName")//这个注解配置的就是转成xml时对应的节点名字
    private String toUserName;//接收方的账号(收到的openid)
    @XStreamAlias("FromUserName")
    private String fromUserName;//开发者的微信号
    @XStreamAlias("CreateTime")
    private String createTime;//消息创建时间
    @XStreamAlias("MsgType")
    private String msgType;//消息类型

    public BaseMsg(Map<String,String> requestMap) {
        super();
        this.toUserName = requestMap.get("FromUserName");
        this.fromUserName = requestMap.get("ToUserName");
        this.createTime = requestMap.get("CreateTime");
    }
}

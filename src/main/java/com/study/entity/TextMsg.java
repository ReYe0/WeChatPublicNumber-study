package com.study.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 图文消息封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("xml") //xml指的就是xml这个根节点名称
public class TextMsg extends BaseMsg {
    @XStreamAlias("Content")
    private String content;//回复的文本内容

    public TextMsg(Map<String,String> requestMap, String content) {
        super(requestMap);
        this.setMsgType("text");
        this.content = content;
    }
}
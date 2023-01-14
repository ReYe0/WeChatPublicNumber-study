package com.study.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图文消息格式，在这基础之上进行封装
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[news]]></MsgType>
 *   <ArticleCount>1</ArticleCount>
 *   <Articles>
 *     <item>
 *       <Title><![CDATA[title1]]></Title>
 *       <Description><![CDATA[description1]]></Description>
 *       <PicUrl><![CDATA[picurl]]></PicUrl>
 *       <Url><![CDATA[url]]></Url>
 *     </item>
 *   </Articles>
 * </xml>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("item")//映射到xml中的item这个节点
public class Article {
    @XStreamAlias("Title")
    private String title;//图文消息标题
    @XStreamAlias("Description")
    private String description;//图文消息描述
    @XStreamAlias("PicUrl")
    private String picUrl;//图片链接
    @XStreamAlias("Url")
    private String url;//点击图文消息跳转链接
}

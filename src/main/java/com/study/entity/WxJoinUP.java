package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 本来打算封装wx接入的请求，但是@RequestBody 失败，猜测应该是参数太多，对应不上实体，因此暂时作废
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxJoinUP {
    private String signature;//	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。//
    private String timestamp;//	时间戳
    private String nonce;//	随机数
    private String echostr;//随机字符串

}

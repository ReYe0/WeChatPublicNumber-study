package com.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AccessToken类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {
    private String token;
    private long expiresTime;//过期时间

    public AccessToken(String token, String expiresIn) {
        super();
        this.token = token;
        //当前时间+有效期 = 过期时间
        this.expiresTime = System.currentTimeMillis()+Integer.parseInt(expiresIn);
    }

    /**
     * 判断token是否过期
     * @return
     */
    public boolean isExpire() {
        return System.currentTimeMillis() > expiresTime;
    }
}
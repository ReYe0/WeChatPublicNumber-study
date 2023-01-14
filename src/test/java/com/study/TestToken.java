package com.study;

import com.study.util.WxUtils;
import org.junit.Test;

public class TestToken {
    @Test
    public void getAccessToken() {
        //可以看到下面两次获取的值一致
        System.out.println(WxUtils.getAccessToken());
        System.out.println(WxUtils.getAccessToken());
    }
}

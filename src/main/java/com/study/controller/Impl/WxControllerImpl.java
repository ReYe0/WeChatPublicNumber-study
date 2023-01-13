package com.study.controller.Impl;

import com.study.controller.WxController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/wx")
public class WxControllerImpl implements WxController {
    @Override
    public String test() {
        return "hello";
    }
}

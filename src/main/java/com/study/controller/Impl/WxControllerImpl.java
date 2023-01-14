package com.study.controller.Impl;

import com.study.controller.WxController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class WxControllerImpl implements WxController {
    @Override
    public String test() {
        return "hello";
    }
}

package com.ehomepay.stamp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ehomepay.stamp.mapper.DemoMapper;

@Controller
public class TestController {
    @Resource
    private DemoMapper demoMapper;
    
    @GetMapping("/test")
    public String getCacheAuditList(){
        return "index";
   }
}

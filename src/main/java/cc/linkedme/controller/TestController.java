package cc.linkedme.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cc.linkedme.mapper.DemoMapper;

@Controller
public class TestController {
    @Resource
    private DemoMapper demoMapper;
    
    @GetMapping("/test")
    public String getCacheAuditList(){
        return "index";
   }
}

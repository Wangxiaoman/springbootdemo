package cc.linkedme.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cc.linkedme.mapper.DemoMapper;

@Controller
public class TestController {
    @Resource
    private DemoMapper demoMapper;
    
    @GetMapping("/test")
    public Map<String,String> getCacheAuditList(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
   }
}

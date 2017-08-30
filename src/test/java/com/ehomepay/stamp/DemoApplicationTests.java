package com.ehomepay.stamp;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cc.linkedme.mapper.DemoMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@EnableFeignClients
public class DemoApplicationTests {
    
    @Resource
    private DemoMapper demoMapper;
    
    @Test
    public void test(){
        System.out.println(demoMapper.queryCount());
    }
    
}
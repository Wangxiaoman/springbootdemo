package com.ehomepay.stamp.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
    
    private static final Logger LOGGER = Logger.getLogger(TestTask.class);
    
    @Scheduled(cron = "0 0 0 * * *")
    public void test() {
        try {
            LOGGER.info("TestTask->test begin:" + System.currentTimeMillis());
            LOGGER.info("TestTask->test end:" + System.currentTimeMillis());
        } catch (Exception e) {
            LOGGER.error("TestTask->test error,ex:", e);
        }
    }
}

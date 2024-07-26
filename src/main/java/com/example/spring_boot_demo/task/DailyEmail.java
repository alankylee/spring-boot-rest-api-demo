package com.example.spring_boot_demo.task;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class DailyEmail {
    
    private static final Logger logger = LoggerFactory.getLogger(DailyEmail.class);

    @Scheduled(cron = "${scheduled.task.email.cron}")
    public void dailyEmailJob() {
        logger.info("Daily Email Job @ {}", Instant.now());
    }

}
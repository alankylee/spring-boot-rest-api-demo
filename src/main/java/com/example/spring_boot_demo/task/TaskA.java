package com.example.spring_boot_demo.task;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class TaskA {
    
    private static final Logger logger = LoggerFactory.getLogger(TaskA.class);

    @Scheduled(initialDelayString = "${scheduled.task.initialDelay}", fixedDelayString = "${scheduled.task.fixedDelay}")
    public void performTaskA() throws InterruptedException {
        logger.info("Performing - A demo task @ {}", Instant.now());
        Thread.sleep(3000L);
    }

}
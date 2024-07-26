package com.example.spring_boot_demo.task;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class TaskB {
    
    private static final Logger logger = LoggerFactory.getLogger(TaskB.class);

    @Scheduled(initialDelayString = "${scheduled.task.initialDelay}", fixedDelayString = "${scheduled.task.fixedDelay}")
    public void performTaskB() {
        logger.info("Performing - B demo task @ {}", Instant.now());
    }

}
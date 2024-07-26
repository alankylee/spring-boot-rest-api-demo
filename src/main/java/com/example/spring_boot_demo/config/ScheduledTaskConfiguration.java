package com.example.spring_boot_demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.spring_boot_demo.task.DailyEmail;
import com.example.spring_boot_demo.task.TaskA;
import com.example.spring_boot_demo.task.TaskB;

@Configuration
@EnableScheduling
public class ScheduledTaskConfiguration {

    @Bean
    @ConditionalOnProperty(name = "scheduled.task.a.enabled", matchIfMissing = false, havingValue = "true")
    public TaskA taskA() {
        return new TaskA();
    }

    @Bean
    @ConditionalOnProperty(name = "scheduled.task.b.enabled", matchIfMissing = false, havingValue = "true")
    public TaskB taskB() {
        return new TaskB();
    }

    @Bean
    @ConditionalOnProperty(name = "scheduled.task.email.enabled", matchIfMissing = false, havingValue = "true")
    public DailyEmail dailyEmail() {
        return new DailyEmail();
    }
    
}

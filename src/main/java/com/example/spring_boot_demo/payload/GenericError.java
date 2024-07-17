package com.example.spring_boot_demo.payload;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class GenericError {
    
    private final String name;
    private final String errorMessage;
    private final List<StackTraceElement> stackTrace;

    public GenericError(Exception exception) {
        this.name = exception.getClass().getSimpleName();
        this.errorMessage = exception.getMessage();
        this.stackTrace = Arrays.asList(exception.getStackTrace()).stream().filter(s -> s.getClassName().contains("com.example")).collect(Collectors.toList());
    }
}
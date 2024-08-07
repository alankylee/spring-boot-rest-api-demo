package com.example.spring_boot_demo.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    M("M"),
    F("F");
    
    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
    
}
package com.example.spring_boot_demo.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Pet {
    CAT("Cat"),
    DOG("Dog"),
    BIRD("Bird");
    
    private final String value;

    Pet(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
    
}
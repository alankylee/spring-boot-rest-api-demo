package com.example.spring_boot_demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private final String accessToken;
    private final String tokenType;
}

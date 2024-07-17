package com.example.spring_boot_demo.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(requiredProperties = { "email", "password" })
public class JwtAuthenticationRequest {

    @Schema(type = "string", format = "email", pattern = "^\\S+@\\S+\\.\\S+$", example = "user@example.com", maxLength = 100)
    private final String email;
    
    @Schema(type = "string", format = "password", example = "examplePassword", minLength = 8)
    private final String password;
}

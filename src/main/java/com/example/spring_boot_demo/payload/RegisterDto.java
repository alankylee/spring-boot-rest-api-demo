package com.example.spring_boot_demo.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(requiredProperties = { "email", "password" })
public class RegisterDto {

    @Schema(type = "string", format = "email", pattern = "^(\\S+)@(\\S+)\\.(\\S+)$", example = "user@example.com", maxLength = 100)
    private String email;
    
    @Schema(type = "string", format = "password", example = "examplePassword", minLength = 8)
    private String password;

    @Schema(type = "string", format = "phone", pattern = "^([0-9]{3})-([0-9]{3})-([0-9]{4})$", example = "312-765-9876")
    private String phone;

}
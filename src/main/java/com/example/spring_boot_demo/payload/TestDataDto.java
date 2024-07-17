package com.example.spring_boot_demo.payload;

import java.time.LocalDate;

import com.example.spring_boot_demo.constant.Gender;
import com.example.spring_boot_demo.constant.Pet;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestDataDto {

    @Schema(type = "number", format = "int64", example = "1", hidden = true)
    private Long id;

    @Schema(type = "number", format = "int32", example = "2147483647")
    private Integer number;

    @Enumerated(EnumType.STRING)
    private Pet pet;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2024-07-01")
    private LocalDate birthday;

}
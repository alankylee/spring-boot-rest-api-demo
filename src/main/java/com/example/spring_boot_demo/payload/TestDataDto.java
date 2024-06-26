package com.example.spring_boot_demo.payload;

import com.example.spring_boot_demo.constant.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TestDataDto {
    private Long id;
    private Integer number;
    private String text;
    private Gender gender;
}
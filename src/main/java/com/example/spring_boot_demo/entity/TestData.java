package com.example.spring_boot_demo.entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.spring_boot_demo.constant.Gender;
import com.example.spring_boot_demo.constant.Pet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "test_data")
public class TestData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_data_id")
    private Long id;

    private Integer number;

    private Pet pet;

    private Gender gender;

    private LocalDate birthday;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

}
package com.example.spring_boot_demo.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.example.spring_boot_demo.constant.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @Column(nullable = true, unique = false)
    private Integer number;

    @Column(length = 50, nullable = true, unique = false)
    private String text;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

}

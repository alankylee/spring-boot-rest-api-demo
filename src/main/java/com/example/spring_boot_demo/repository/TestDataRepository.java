package com.example.spring_boot_demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_boot_demo.entity.TestData;

public interface TestDataRepository extends JpaRepository<TestData, Long>{

    Optional<TestData> findById(long id);

}
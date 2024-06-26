package com.example.spring_boot_demo.service;

import java.util.List;

import com.example.spring_boot_demo.payload.TestDataDto;

public interface HealthCheckService {

    void save(TestDataDto newDataDto);

    void deletebyId(long id);

    void deleteAll();
    
    List<TestDataDto> findAll(int pageNo, int pageSize, String sortBy, String sortDir);

    TestDataDto findById(long id);
    
}
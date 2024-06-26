package com.example.spring_boot_demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.spring_boot_demo.entity.TestData;
import com.example.spring_boot_demo.exception.ResourceNotFoundException;
import com.example.spring_boot_demo.payload.TestDataDto;
import com.example.spring_boot_demo.repository.TestDataRepository;
import com.example.spring_boot_demo.service.HealthCheckService;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {

    private final TestDataRepository testDataRepo;
    private final ModelMapper mapper;

    public HealthCheckServiceImpl(TestDataRepository testDataRepo, ModelMapper mapper) {
        this.testDataRepo = testDataRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(TestDataDto testDataDto) {
        TestData testData = mapper.map(testDataDto, TestData.class);
        testDataRepo.save(testData);
    }

    @Override
    public void deletebyId(long id) {
        testDataRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        testDataRepo.deleteAll();
    }

    @Override
    public List<TestDataDto> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<TestData> testDataPage = testDataRepo.findAll(pageable);
        // get content for page object
        List<TestData> testDataList = testDataPage.getContent();

        return testDataList.stream().map(testData -> mapper.map(testData, TestDataDto.class)).collect(Collectors.toList());
    }

    @Override
    public TestDataDto findById(long id) {
        TestData testData = testDataRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("TestData", "id", id));

        return mapper.map(testData, TestDataDto.class);
    }

}

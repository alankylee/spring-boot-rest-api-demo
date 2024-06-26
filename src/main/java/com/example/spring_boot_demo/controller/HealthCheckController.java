package com.example.spring_boot_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_demo.constant.AppConstants;
import com.example.spring_boot_demo.payload.TestDataDto;
import com.example.spring_boot_demo.service.HealthCheckService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Health Check")
@RestController
@RequestMapping("/api/healthcheck")
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;

    

    @Operation(
            summary = "Application Health Check",
            description = "Check if the application starts normally."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<Object> check() {
        return new ResponseEntity<>("<h1>It works!</h1>", HttpStatus.OK);
    }



    @Operation(
            summary = "List all testing data.",
            description = "To list all testing data on the page in plain text."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/list")
    public ResponseEntity<Object> list(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
        ) {
        List<TestDataDto> testDataDtoList = healthCheckService.findAll(pageNo, pageSize, sortBy, sortDir);
        String listStr = "";

        for (TestDataDto testDataDto : testDataDtoList) {
            listStr += "<br>" + testDataDto.toString();
        }

        return new ResponseEntity<>("<b>Data List</b>: " + listStr, HttpStatus.OK);
    }



    @Operation(
            summary = "Get all testing data",
            description = "To get all testing data and return in JSON format."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/all")
    public ResponseEntity<Object> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
        ) {
        List<TestDataDto> testDataDtoList = healthCheckService.findAll(pageNo, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(testDataDtoList, HttpStatus.OK);
    }



    @Operation(
            summary = "Get a testing data by id",
            description = "To get a testing data by id and return in JSON format."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@Parameter(description = "Id of the testing data") @PathVariable("id") Long id) {
        TestDataDto testDataDto = healthCheckService.findById(id);

        return new ResponseEntity<>(testDataDto, HttpStatus.OK);
    }



    @Operation(
            summary = "Create testing data",
            description = "To create a testing data and save it into database."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> create(@RequestBody TestDataDto newDataDto) {
        if (newDataDto.getId() != null) {
            newDataDto.setId(null);
        }
        healthCheckService.save(newDataDto);

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }



    @Operation(
            summary = "Update testing data",
            description = "To update a testing data by id and save it into database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PutMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> update(@RequestBody TestDataDto updateDataDto) {
        TestDataDto testDataDto = healthCheckService.findById(updateDataDto.getId());
        if (testDataDto == null) {
            return new ResponseEntity<>("Cannot find the data", HttpStatus.BAD_REQUEST);
        }
        healthCheckService.save(testDataDto);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }



    @Operation(
            summary = "Delete testing data by id.",
            description = "To delete a testing data from database by id."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@Parameter(description = "Id of the testing data") @PathVariable("id") Long id) {
        healthCheckService.deletebyId(id);

        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }



    @Operation(
            summary = "Delete all testing data",
            description = "To delete all testing data from database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/all")
    public ResponseEntity<Object> deleteAll() {
        healthCheckService.deleteAll();

        return new ResponseEntity<>("Deleted all", HttpStatus.OK);
    }



}
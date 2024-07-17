package com.example.spring_boot_demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.example.spring_boot_demo.constant.SwaggerExamples.TestDataExamples;
import com.example.spring_boot_demo.payload.GenericResponseEntity;
import com.example.spring_boot_demo.payload.TestDataDto;
import com.example.spring_boot_demo.service.TestDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Test Data")
@RestController
@RequestMapping("/api/testdata")
public class TestDataController {

    private final TestDataService testDataService;

    public TestDataController(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    @Operation(
            summary = "Get a testing data by id",
            description = "To get a testing data by id and return in JSON format.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = TestDataExamples.FIND_SUCCESS_RESPONSE)))
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseEntity<Object>> find(@Parameter(description = "Id of the testing data") @PathVariable("id") Long id) {
        try {
            TestDataDto testDataDto = testDataService.findById(id);
            return GenericResponseEntity.ok("Success.", testDataDto);
        } catch (Exception e) {
            return GenericResponseEntity.badRequest(e.getMessage());
        }
    }

    @Operation(
            summary = "Get all testing data",
            description = "To get all testing data and return in JSON format.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = TestDataExamples.FIND_ALL_SUCCESS_RESPONSE)))
    @GetMapping("/all")
    public ResponseEntity<GenericResponseEntity<Object>> findAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        try {
            List<TestDataDto> testDataDtoList = testDataService.findAll(pageNo, pageSize, sortBy, sortDir);
            return GenericResponseEntity.ok("Success.", testDataDtoList);
        } catch (Exception e) {
            return GenericResponseEntity.badRequest(e.getMessage());
        }
    }

    @Operation(
            summary = "Download the document by path",
            description = "To get a testing data by id and return in JSON format.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                    examples = @ExampleObject(value = TestDataExamples.DOWNLOAD_SUCCESS_RESPONSE)))
    @GetMapping("/download")
    public ResponseEntity<?> download(@RequestParam String pathname) {
        try {
            var file = new File(pathname);
            var resource = new InputStreamResource(new FileInputStream(file));
            var header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Create testing data",
            description = "To create a testing data and save it into database.",
            security = @SecurityRequirement(name = "Bear Authentication"))
    @ApiResponse(
            responseCode = "200",
            content = @Content(examples = @ExampleObject(value = TestDataExamples.CREATE_SUCCESS_RESPONSE)))
    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseEntity<Object>> create(@RequestBody TestDataDto newDataDto) {
        try {
            if (newDataDto.getId() != null) {
                return GenericResponseEntity.badRequest("For creation, ID must be null.");
            }
            testDataService.save(newDataDto);
            return GenericResponseEntity.ok("Craeted.");
        } catch (Exception e) {
            return GenericResponseEntity.badRequest(e.getMessage());
        }
    }

    @Operation(
            summary = "Update testing data",
            description = "To update a testing data by id and save it into database.",
            security = @SecurityRequirement(name = "Bear Authentication"))
    @ApiResponse(
            responseCode = "200",
            content = @Content(examples = @ExampleObject(value = TestDataExamples.UPDATE_SUCCESS_RESPONSE)))
    @PutMapping(
            path = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponseEntity<Object>> update(
            @Parameter(description = "Id of the testing data") @PathVariable("id") Long id,
            @RequestBody TestDataDto updateDataDto) {
        try {
            updateDataDto.setId(id);
            TestDataDto testDataDto = testDataService.findById(updateDataDto.getId());
            if (testDataDto == null) {
                return GenericResponseEntity.notFound("Cannot find the data.");
            }
            testDataService.save(testDataDto);
            return GenericResponseEntity.ok("Updated.");
        } catch (Exception e) {
            return GenericResponseEntity.badRequest(e.getMessage());
        }
    }

    @Operation(
            summary = "Delete testing data by id.",
            description = "To delete a testing data from database by id.",
            security = @SecurityRequirement(name = "Bear Authentication"))
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = TestDataExamples.DELETE_SUCCESS_RESPONSE)))
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseEntity<Object>> delete(@Parameter(description = "Id of the testing data") @PathVariable("id") Long id) {
        try {
            testDataService.deletebyId(id);
            return GenericResponseEntity.ok("Deleted.");
        } catch (Exception e) {
            return GenericResponseEntity.badRequest(e.getMessage());
        }
    }

    @Operation(
            summary = "Delete all testing data",
            description = "To delete all testing data from database.",
            security = @SecurityRequirement(name = "Bear Authentication"))
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(value = TestDataExamples.DELETE_ALL_SUCCESS_RESPONSE)))
    @DeleteMapping("/all")
    public ResponseEntity<GenericResponseEntity<Object>> deleteAll() {
        try {
            testDataService.deleteAll();
            return GenericResponseEntity.ok("Deleted all.");
        } catch (Exception e) {
            return GenericResponseEntity.badRequest(e.getMessage());
        }
    }

}

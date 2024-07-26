package com.example.spring_boot_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_demo.constant.SwaggerExamples.HealthCheckExamples;
import com.example.spring_boot_demo.payload.GenericResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Health Check")
@RestController
@RequestMapping("/api/healthcheck")
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @Operation(
            summary = "Application Health Check",
            description = "Check if the application starts normally.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = HealthCheckExamples.CHECK_SUCCESS_RESPONSE)))
    @GetMapping
    public ResponseEntity<GenericResponseEntity<Object>> check() {
        logger.debug("Checking...");
        return GenericResponseEntity.ok("It works!");
    }

}

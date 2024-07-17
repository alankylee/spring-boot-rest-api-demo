package com.example.spring_boot_demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_demo.constant.SwaggerExamples.AuthenticationExamples;
import com.example.spring_boot_demo.payload.GenericError;
import com.example.spring_boot_demo.payload.GenericResponseEntity;
import com.example.spring_boot_demo.payload.JwtAuthenticationRequest;
import com.example.spring_boot_demo.payload.JwtAuthenticationResponse;
import com.example.spring_boot_demo.payload.RegisterDto;
import com.example.spring_boot_demo.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication")
@SecurityRequirements() // Override the default security requirements that defined in the main class
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Login",
            description = "Login with your Email and password.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(examples = @ExampleObject(value = AuthenticationExamples.LOGIN_SUCCESS_RESPONSE)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(examples = @ExampleObject(value = AuthenticationExamples.LOGIN_ERROR_RESPONSE)))
    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseEntity<Object>> login(@RequestBody JwtAuthenticationRequest jwtAuthReq) {
        try {
            JwtAuthenticationResponse jwtAuthResp = authService.login(jwtAuthReq);
            return GenericResponseEntity.ok("Login successful", jwtAuthResp);
        } catch (Exception e) {
            return GenericResponseEntity.badRequest(e.getMessage());
        }
    }

    @Operation(
            summary = "Register",
            description = "Create an account using email.")
    @ApiResponse(
            responseCode = "201",
            content = @Content(examples = @ExampleObject(value = AuthenticationExamples.REGISTER_SUCCESS_RESPONSE)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(examples = @ExampleObject(value = AuthenticationExamples.REGISTER_ERROR_RESPONSE)))
    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseEntity<Object>> register(@RequestBody RegisterDto registerDto) {
        try {
            authService.register(registerDto);
            return GenericResponseEntity.created("Registration successful");
        } catch (Exception e) {
            return GenericResponseEntity.badRequest(e.getMessage());
        }
    }

}

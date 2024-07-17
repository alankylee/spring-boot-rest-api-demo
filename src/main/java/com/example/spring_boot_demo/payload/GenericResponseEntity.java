package com.example.spring_boot_demo.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenericResponseEntity<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> ResponseEntity<GenericResponseEntity<T>> success(HttpStatus httpStatus, String message, T data) {
        return ResponseEntity
                .status(httpStatus)
                .body(GenericResponseEntity.<T>builder()
                        .success(true)
                        .message(message)
                        .data(data)
                        .build());
    }

    public static <T> ResponseEntity<GenericResponseEntity<T>> ok(String message, T data) {
        return success(HttpStatus.OK, message, data);
    }

    public static <T> ResponseEntity<GenericResponseEntity<T>> ok(String message) {
        return ok(message, null);
    }

    public static <T> ResponseEntity<GenericResponseEntity<T>> created(String message, T data) {
        return success(HttpStatus.CREATED, message, data);
    }

    public static <T> ResponseEntity<GenericResponseEntity<T>> created(String message) {
        return created(message, null);
    }

    public static <T> ResponseEntity<GenericResponseEntity<T>> error(HttpStatus httpStatus, String message, T error) {
        return ResponseEntity
                .status(httpStatus)
                .body(GenericResponseEntity.<T>builder()
                        .success(false)
                        .message(message)
                        .data(error)
                        .build());
    }

    public static <T> ResponseEntity<GenericResponseEntity<T>> badRequest(String message, T error) {
        return error(HttpStatus.BAD_REQUEST, message, error);
    }

    public static <T> ResponseEntity<GenericResponseEntity<T>> badRequest(String message) {
        return badRequest(message, null);
    }

    public static <T> ResponseEntity<GenericResponseEntity<T>> notFound(String message, T error) {
        return error(HttpStatus.NOT_FOUND, message, error);
    }

    public static <T> ResponseEntity<GenericResponseEntity<T>> notFound(String message) {
        return notFound(message, null);
    }

}
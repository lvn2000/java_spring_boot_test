package com.example.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler for centralized error handling across all REST endpoints
 * 
 * This handler ensures consistent error response format across the application:
 * - All errors return JSON format with status, message, timestamp
 * - Logging is centralized
 * - Error codes are standardized
 * 
 * Replaces scattered error handling in individual controllers
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * Handle IllegalArgumentException - typically raised by validation failures
     * Status: 400 BAD_REQUEST
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException e) {
        logger.warn("Returning 400 Bad Request: {}", e.getMessage());
        return ResponseEntity.badRequest().body(buildErrorResponse(
            400,
            "Bad Request",
            e.getMessage()
        ));
    }
    
    /**
     * Handle NumberFormatException - for invalid numeric parameters
     * Status: 400 BAD_REQUEST
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Map<String, Object>> handleNumberFormat(NumberFormatException e) {
        logger.warn("Returning 400 Bad Request - Number format error: {}", e.getMessage());
        return ResponseEntity.badRequest().body(buildErrorResponse(
            400,
            "Invalid Number Format",
            "The provided parameter is not a valid number"
        ));
    }
    
    /**
     * Handle NullPointerException - programming errors
     * Status: 500 INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, Object>> handleNullPointer(NullPointerException e) {
        logger.error("NullPointerException occurred", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
            500,
            "Internal Server Error",
            "An unexpected error occurred"
        ));
    }
    
    /**
     * Handle generic RuntimeException
     * Status: 500 INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        logger.error("RuntimeException occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
            500,
            "Internal Server Error",
            e.getMessage() != null ? e.getMessage() : "An unexpected error occurred"
        ));
    }
    
    /**
     * Handle any other Exception
     * Status: 500 INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception e) {
        logger.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildErrorResponse(
            500,
            "Internal Server Error",
            "An unexpected error occurred. Please contact support."
        ));
    }
    
    /**
     * Build standardized error response JSON
     */
    private Map<String, Object> buildErrorResponse(int status, String error, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("error", error);
        response.put("message", message);
        response.put("timestamp", LocalDateTime.now());
        return response;
    }
}

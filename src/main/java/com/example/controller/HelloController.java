package com.example.controller;

import com.example.model.ExternalApiResponse;
import com.example.model.LocaleData;
import com.example.service.ExternalApiService;
import com.example.service.LocaleStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * REST Controller for country info with language locale transformation and database storage
 * 
 * NEW: Now uses GlobalExceptionHandler for centralized error handling
 * No need for try-catch blocks here - exceptions are handled globally
 */
@RestController
public class HelloController {
    
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    
    private final ExternalApiService externalApiService;
    
    @Autowired
    public HelloController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    /**
     * Get country info by ID, transform language locale, and store result in database
     * 
     * Single endpoint that:
     * 1. Receives country ID as query parameter
     * 2. Calls external REST API with the ID
     * 3. Receives response with lang (e.g., "en", "sv", "lv") and country (e.g., "US", "SE", "LV")
     * 4. Transforms lang according to rules:
     *    - If lang="en", maps to en-US, en-CA, en-GB, etc. based on country
     *    - If lang is other, maps to sv-SE, lv-LV, etc.
     * 5. Saves transformation to database with transaction management
     * 6. Returns transformed response
     * 
     * Error handling: Exceptions are handled by GlobalExceptionHandler (@RestControllerAdvice)
     * Returns consistent JSON error responses
     */
    @GetMapping("/country-info")
    public ResponseEntity<ExternalApiResponse> getCountryInfo(
            @RequestParam(name = "id") Long countryId) {
        
        logger.info("Processing request: /country-info?id={}", countryId);
        
        ExternalApiResponse transformedResponse = externalApiService.getAndTransformLocale(countryId);
        
        if (transformedResponse != null) {
            logger.info("Successfully processed country info for id={} with transformed lang: {}", 
                countryId, transformedResponse.getLang());
            return ResponseEntity.ok(transformedResponse);
        } else {
            logger.error("External API returned no data for id={}", countryId);
            return ResponseEntity.noContent().build();
        }
    }
}


package com.example.controller;

import com.example.config.MockApiConfig;
import com.example.model.ExternalApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Mock External API Controller for testing
 * 
 * This controller simulates an external API that returns lang and country parameters.
 * Used for testing the transformation logic.
 * 
 * NEW: Country mappings are now externalized to application.properties via MockApiConfig
 * This allows adding/modifying country mappings without code changes
 */
@RestController
@RequestMapping("/api/external")
public class MockExternalApiController {
    
    private static final Logger logger = LoggerFactory.getLogger(MockExternalApiController.class);
    private final MockApiConfig mockApiConfig;
    
    @Autowired
    public MockExternalApiController(MockApiConfig mockApiConfig) {
        this.mockApiConfig = mockApiConfig;
    }
    
    /**
     * GET endpoint simulating external API response
     * Returns lang as enum (en, lv, se, etc.) and country code
     * 
     * Example: /api/external/data?lang=en&country=US
     *          /api/external/data?lang=sv&country=SE
     *          /api/external/data?lang=lv&country=LV
     */
    @GetMapping("/data")
    public ResponseEntity<ExternalApiResponse> getExternalData(
            @RequestParam(value = "lang", defaultValue = "en") String lang,
            @RequestParam(value = "country", defaultValue = "US") String country) {
        
        ExternalApiResponse response = new ExternalApiResponse();
        response.setId("ext-123");
        response.setName("External API Response");
        response.setLang(lang);
        response.setCountry(country);
        response.setDescription("Mock response from external API with lang=" + lang + " and country=" + country);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Default GET endpoint that accepts country ID parameter (numeric)
     * Returns simulated response based on country ID
     * 
     * Country mappings are loaded from application.properties via MockApiConfig
     * Default fallback: en|US
     */
    @GetMapping
    public ResponseEntity<ExternalApiResponse> getByCountryId(
            @RequestParam(value = "id", defaultValue = "1") Long countryId) {
        
        logger.debug("Processing mock API request for country ID: {}", countryId);
        
        // Get country mapping from externalized configuration
        Map<String, String> mapping = mockApiConfig.getCountryMapping(countryId);
        String lang = mapping.get("lang");
        String country = mapping.get("country");
        
        logger.debug("Mapped country ID {} to lang='{}', country='{}'", countryId, lang, country);
        
        ExternalApiResponse response = new ExternalApiResponse();
        response.setId(String.valueOf(countryId));
        response.setName("Country: " + countryId);
        response.setLang(lang);
        response.setCountry(country);
        response.setDescription("Mock response from external API for country ID: " + countryId);
        
        logger.debug("Returning mock API response: {}", response);
        return ResponseEntity.ok(response);
    }
}

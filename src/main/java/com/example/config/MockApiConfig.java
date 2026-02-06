package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Mock API country mappings
 * 
 * Reads properties in format:
 * mock.api.countries.1=en|US
 * mock.api.countries.2=sv|SE
 * ...
 * 
 * This externalizes the hardcoded switch statement from MockExternalApiController
 * allowing runtime configuration changes without code recompilation.
 * 
 * Can be extended to load from database or external service
 */
@Component
@ConfigurationProperties(prefix = "mock.api")
public class MockApiConfig {
    
    /**
     * Map of country ID to country mapping
     * Key: country ID (e.g., "1", "2", "3")
     * Value: "lang|country" format (e.g., "en|US", "sv|SE")
     */
    private Map<String, String> countries = new HashMap<>();
    
    /**
     * Get the language and country for a given country ID
     * 
     * @param countryId The country ID as Long
     * @return Map with "lang" and "country" keys, or default (en|US) if not found
     */
    public Map<String, String> getCountryMapping(Long countryId) {
        String mapping = countries.getOrDefault(String.valueOf(countryId), "en|US");
        String[] parts = mapping.split("\\|");
        
        Map<String, String> result = new HashMap<>();
        result.put("lang", parts.length > 0 ? parts[0] : "en");
        result.put("country", parts.length > 1 ? parts[1] : "US");
        return result;
    }
    
    /**
     * Get all country mappings
     */
    public Map<String, String> getCountries() {
        return countries;
    }
    
    /**
     * Set country mappings (called by Spring during property initialization)
     */
    public void setCountries(Map<String, String> countries) {
        this.countries = countries;
    }
    
    /**
     * Check if a country ID mapping exists
     */
    public boolean hasCountry(Long countryId) {
        return countries.containsKey(String.valueOf(countryId));
    }
}

package com.example.service;

import com.example.model.ExternalApiResponse;
import com.example.model.LocaleData;
import com.example.transformer.LanguageTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

/**
 * Service for calling external API, transforming language parameters, and storing results
 * 
 * NEW: Now uses LanguageTransformer interface for language transformation
 * This decouples from specific enum implementation, enabling alternative strategies
 * 
 * Rules:
 * - If lang="en", map to country-specific variant: en-US, en-CA, en-GB, etc.
 * - If lang is other (sv, lv, etc.), map to corresponding locale: sv-SE, lv-LV, etc.
 * - All results are saved to database with transaction management and error handling
 */
@Service
public class ExternalApiService {
    
    private static final Logger logger = LoggerFactory.getLogger(ExternalApiService.class);
    
    private final RestTemplate restTemplate;
    private final LocaleStorageService localeStorageService;
    private final LanguageTransformer languageTransformer;
    
    @Value("${external.api.url:http://localhost:8080/api/external}")
    private String externalApiUrl;

    @Autowired
    public ExternalApiService(RestTemplate restTemplate, 
                            LocaleStorageService localeStorageService,
                            LanguageTransformer languageTransformer) {
        this.restTemplate = restTemplate;
        this.localeStorageService = localeStorageService;
        this.languageTransformer = languageTransformer;
    }

    /**
     * Call external API with country ID, transform language locale, and store result in database
     * Uses transaction to ensure data consistency
     * 
     * @param countryId Country identifier (numeric) to fetch from external API
     * @return ExternalApiResponse with transformed lang parameter and saved to database
     */
    @Transactional
    public ExternalApiResponse getAndTransformLocale(Long countryId) {
        try {
            // Construct API URL with country ID parameter
            String apiUrlWithId = externalApiUrl + "?id=" + countryId;
            logger.info("Calling external API: {}", apiUrlWithId);
            
            // Call external API with country ID
            ExternalApiResponse response = restTemplate.getForObject(
                    apiUrlWithId,
                    ExternalApiResponse.class
            );
            
            if (response == null) {
                logger.error("External API returned null response");
                localeStorageService.saveFailedTransformation(null, null, "External API returned null response");
                return null;
            }
            
            logger.debug("Received response from external API: {}", response);
            
            // Transform lang parameter
            String originalLang = response.getLang();
            String originalCountry = response.getCountry();
            String transformedLang = transformLanguageLocale(originalLang, originalCountry);
            response.setLang(transformedLang);
            
            logger.info("Successfully transformed lang from '{}' to '{}'", originalLang, transformedLang);
            
            // Save successful transformation to database
            LocaleData savedData = localeStorageService.saveSuccessfulTransformation(
                    originalLang, 
                    originalCountry, 
                    transformedLang,
                    response.getId(),
                    response.getName()
            );
            
            logger.debug("Transformation saved to database with ID: {}", savedData.getId());
            
            return response;
            
        } catch (RestClientException e) {
            logger.error("Error calling external API: {}", externalApiUrl, e);
            
            // Save error to database
            try {
                localeStorageService.saveFailedTransformation(null, null, 
                    "API call failed: " + e.getMessage());
            } catch (Exception saveError) {
                logger.error("Failed to save error record to database", saveError);
            }
            
            throw new RuntimeException("Failed to call external API", e);
            
        } catch (Exception e) {
            logger.error("Unexpected error during locale transformation: {}", e.getMessage(), e);
            
            // Save error to database
            try {
                localeStorageService.saveFailedTransformation(null, null, 
                    "Unexpected error: " + e.getMessage());
            } catch (Exception saveError) {
                logger.error("Failed to save error record to database", saveError);
            }
            
            throw new RuntimeException("Failed to transform locale", e);
        }
    }

    /**
     * Transform language code based on country
     * 
     * Rules:
     * - If lang="en", map to country-specific variant (en-US, en-CA, en-GB, etc.)
     * - For other languages, map to corresponding country locale (sv-SE, lv-LV, etc.)
     * 
     * Delegates to LanguageTransformer interface for actual transformation logic
     * 
     * @param lang the base language code (e.g., "en", "sv", "lv")
     * @param country the country code (e.g., "US", "SE", "LV")
     * @return the mapped locale code (e.g., "en-US", "sv-SE", "lv-LV")
     */
    public String transformLanguageLocale(String lang, String country) {
        if (lang == null || lang.isEmpty()) {
            logger.warn("Language is null or empty");
            return lang;
        }
        
        if (country == null || country.isEmpty()) {
            logger.warn("Country is null or empty, returning original lang: {}", lang);
            return lang;
        }
        
        // Use injected LanguageTransformer interface for transformation
        try {
            String transformed = languageTransformer.transform(lang, country);
            logger.debug("Successfully transformed '{}' + '{}' to '{}'", lang, country, transformed);
            return transformed;
        } catch (IllegalArgumentException e) {
            // If transformation fails, return original lang with fallback
            logger.warn("Transformation failed for lang='{}', country='{}': {}. Returning original lang.", 
                lang, country, e.getMessage());
            return lang;
        }
    }

    /**
     * Set external API URL (for testing purposes)
     */
    public void setExternalApiUrl(String url) {
        this.externalApiUrl = url;
        logger.info("External API URL set to: {}", url);
    }

    /**
     * Get current external API URL
     */
    public String getExternalApiUrl() {
        return externalApiUrl;
    }
}


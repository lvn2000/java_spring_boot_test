package com.example.transformer;

import com.example.model.LanguageLocale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Language Transformer implementation using LanguageLocale enum
 * 
 * This is the current implementation that uses the predefined LanguageLocale enum
 * to transform language and country codes.
 * 
 * Can be replaced with alternative implementations:
 * - DatabaseLanguageTransformer: Load mappings from database
 * - ExternalServiceTransformer: Load mappings from external service
 * - ConfigFileTransformer: Load mappings from properties file
 */
@Component
public class EnumLanguageTransformer implements LanguageTransformer {
    
    private static final Logger logger = LoggerFactory.getLogger(EnumLanguageTransformer.class);
    
    /**
     * Transform language and country codes using LanguageLocale enum mappings
     * 
     * Delegates to LanguageLocale.mapByCountry() for the actual transformation logic
     * 
     * @param lang Language code (e.g., "en", "sv", "lv", "pt")
     * @param country Country code (e.g., "US", "SE", "LV", "BR", "GB")
     * @return Transformed locale string (e.g., "en-US", "sv-SE", "lv-LV")
     * @throws IllegalArgumentException if mapping fails
     */
    @Override
    public String transform(String lang, String country) {
        try {
            logger.debug("Transforming language '{}' with country '{}'", lang, country);
            LanguageLocale locale = LanguageLocale.mapByCountry(lang, country);
            if (locale == null) {
                throw new IllegalArgumentException("No mapping found for lang=" + lang + ", country=" + country);
            }
            String transformed = locale.getCode();
            logger.debug("Successfully transformed to '{}'", transformed);
            return transformed;
        } catch (Exception e) {
            logger.error("Failed to transform language='{}', country='{}': {}", lang, country, e.getMessage());
            throw new IllegalArgumentException(
                "Invalid language or country combination: " + lang + " / " + country, e
            );
        }
    }
}

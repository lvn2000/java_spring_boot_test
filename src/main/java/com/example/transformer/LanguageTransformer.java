package com.example.transformer;

/**
 * Interface for language locale transformation strategies
 * 
 * Implementations can provide different strategies for transforming
 * language and country codes into standardized locale formats.
 * 
 * This enables pluggable transformation strategies without coupling
 * to specific implementations.
 */
public interface LanguageTransformer {
    
    /**
     * Transform language and country codes to a standardized locale format
     * 
     * @param lang Language code (e.g., "en", "sv", "lv", "pt")
     * @param country Country code (e.g., "US", "SE", "LV", "BR", "GB")
     * @return Transformed locale string (e.g., "en-US", "sv-SE", "lv-LV")
     * @throws IllegalArgumentException if lang or country are invalid
     */
    String transform(String lang, String country);
}

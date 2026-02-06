package com.example.service;

import com.example.model.LocaleData;
import com.example.repository.LocaleDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for storing and retrieving locale transformation data from database
 * Handles transactions and error management
 */
@Service
public class LocaleStorageService {
    
    private static final Logger logger = LoggerFactory.getLogger(LocaleStorageService.class);
    
    private final LocaleDataRepository localeDataRepository;
    
    @Autowired
    public LocaleStorageService(LocaleDataRepository localeDataRepository) {
        this.localeDataRepository = localeDataRepository;
    }

    /**
     * Save successful locale transformation to database
     * Uses transaction to ensure data consistency
     * 
     * @param originalLang the original language code
     * @param originalCountry the original country code
     * @param transformedLang the transformed language locale
     * @param apiResponseId the external API response ID
     * @param apiResponseName the external API response name
     * @return saved LocaleData entity
     */
    @Transactional
    public LocaleData saveSuccessfulTransformation(String originalLang, String originalCountry, 
                                                   String transformedLang, String apiResponseId, 
                                                   String apiResponseName) {
        try {
            logger.debug("Saving successful transformation: {} + {} -> {}", 
                    originalLang, originalCountry, transformedLang);
            
            LocaleData localeData = new LocaleData(originalLang, originalCountry, transformedLang, 
                    apiResponseId, apiResponseName);
            
            LocaleData saved = localeDataRepository.save(localeData);
            
            logger.info("Successfully saved locale transformation to database with ID: {}", saved.getId());
            return saved;
            
        } catch (Exception e) {
            logger.error("Failed to save successful transformation to database: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save transformation to database", e);
        }
    }

    /**
     * Save failed locale transformation to database
     * Uses transaction to ensure data consistency
     * 
     * @param originalLang the original language code
     * @param originalCountry the original country code
     * @param errorMessage the error message describing the failure
     * @return saved LocaleData entity with error information
     */
    @Transactional
    public LocaleData saveFailedTransformation(String originalLang, String originalCountry, 
                                              String errorMessage) {
        try {
            logger.debug("Saving failed transformation: {} + {} - Error: {}", 
                    originalLang, originalCountry, errorMessage);
            
            LocaleData localeData = new LocaleData(originalLang, originalCountry, errorMessage);
            
            LocaleData saved = localeDataRepository.save(localeData);
            
            logger.warn("Saved failed locale transformation to database with ID: {} - Error: {}", 
                    saved.getId(), errorMessage);
            return saved;
            
        } catch (Exception e) {
            logger.error("Failed to save failed transformation to database: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save error record to database", e);
        }
    }

    /**
     * Retrieve all successful transformations
     * 
     * @return list of successful LocaleData records
     */
    @Transactional(readOnly = true)
    public List<LocaleData> getAllSuccessfulTransformations() {
        try {
            logger.debug("Retrieving all successful transformations");
            return localeDataRepository.findBySuccess(true);
        } catch (Exception e) {
            logger.error("Failed to retrieve successful transformations: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve transformations", e);
        }
    }

    /**
     * Retrieve all failed transformations
     * 
     * @return list of failed LocaleData records
     */
    @Transactional(readOnly = true)
    public List<LocaleData> getAllFailedTransformations() {
        try {
            logger.debug("Retrieving all failed transformations");
            return localeDataRepository.findBySuccess(false);
        } catch (Exception e) {
            logger.error("Failed to retrieve failed transformations: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve transformations", e);
        }
    }

    /**
     * Retrieve transformations by language and country combination
     * 
     * @param originalLang the original language code
     * @param originalCountry the original country code
     * @return list of matching LocaleData records
     */
    @Transactional(readOnly = true)
    public List<LocaleData> getTransformationsByLanguageAndCountry(String originalLang, 
                                                                    String originalCountry) {
        try {
            logger.debug("Retrieving transformations for lang={}, country={}", 
                    originalLang, originalCountry);
            return localeDataRepository.findByOriginalLangAndOriginalCountry(originalLang, originalCountry);
        } catch (Exception e) {
            logger.error("Failed to retrieve transformations: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve transformations", e);
        }
    }

    /**
     * Get total count of transformations
     * 
     * @return total number of records in database
     */
    @Transactional(readOnly = true)
    public long getTotalTransformations() {
        try {
            long count = localeDataRepository.count();
            logger.debug("Total transformations in database: {}", count);
            return count;
        } catch (Exception e) {
            logger.error("Failed to get transformation count: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to get transformation count", e);
        }
    }
}

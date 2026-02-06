package com.example.service;

import com.example.model.LanguageLocale;
import com.example.model.LocaleData;
import com.example.repository.LocaleDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit and integration tests for locale transformation and database storage
 */
@SpringBootTest
public class ExternalApiServiceTest {
    
    @Autowired
    private ExternalApiService externalApiService;
    
    @Autowired
    private LocaleStorageService localeStorageService;
    
    @Autowired
    private LocaleDataRepository localeDataRepository;
    
    @Test
    public void testTransformLanguageLocale_EnglishWithUS() {
        String result = externalApiService.transformLanguageLocale("en", "US");
        assertEquals("en-US", result, "Should transform 'en' + 'US' to 'en-US'");
    }
    
    @Test
    public void testTransformLanguageLocale_EnglishWithGB() {
        String result = externalApiService.transformLanguageLocale("en", "GB");
        assertEquals("en-GB", result, "Should transform 'en' + 'GB' to 'en-GB'");
    }
    
    @Test
    public void testTransformLanguageLocale_Swedish() {
        String result = externalApiService.transformLanguageLocale("sv", "SE");
        assertEquals("sv-SE", result, "Should transform 'sv' + 'SE' to 'sv-SE'");
    }
    
    @Test
    public void testTransformLanguageLocale_Latvian() {
        String result = externalApiService.transformLanguageLocale("lv", "LV");
        assertEquals("lv-LV", result, "Should transform 'lv' + 'LV' to 'lv-LV'");
    }
    
    @Test
    public void testSaveSuccessfulTransformation() {
        // Save transformation
        LocaleData saved = localeStorageService.saveSuccessfulTransformation(
            "en", "US", "en-US", "test-123", "Test Response"
        );
        
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("en", saved.getOriginalLang());
        assertEquals("US", saved.getOriginalCountry());
        assertEquals("en-US", saved.getTransformedLang());
        assertTrue(saved.isSuccess());
        
        // Verify it was saved to database
        LocaleData retrieved = localeDataRepository.findById(saved.getId()).orElse(null);
        assertNotNull(retrieved);
        assertEquals("en-US", retrieved.getTransformedLang());
    }
    
    @Test
    public void testSaveFailedTransformation() {
        // Save failed transformation
        LocaleData saved = localeStorageService.saveFailedTransformation(
            "xx", "ZZ", "Invalid locale code"
        );
        
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("xx", saved.getOriginalLang());
        assertEquals("ZZ", saved.getOriginalCountry());
        assertFalse(saved.isSuccess());
        assertEquals("Invalid locale code", saved.getErrorMessage());
        
        // Verify it was saved to database
        LocaleData retrieved = localeDataRepository.findById(saved.getId()).orElse(null);
        assertNotNull(retrieved);
        assertFalse(retrieved.isSuccess());
    }
    
    @Test
    public void testGetSuccessfulTransformations() {
        // Save successful transformation
        localeStorageService.saveSuccessfulTransformation("en", "US", "en-US", "id1", "name1");
        localeStorageService.saveSuccessfulTransformation("sv", "SE", "sv-SE", "id2", "name2");
        
        // Retrieve
        List<LocaleData> successful = localeStorageService.getAllSuccessfulTransformations();
        
        assertTrue(successful.size() >= 2);
        assertTrue(successful.stream().allMatch(LocaleData::isSuccess));
    }
    
    @Test
    public void testGetFailedTransformations() {
        // Save failed transformation
        localeStorageService.saveFailedTransformation("xx", "ZZ", "Error");
        
        // Retrieve
        List<LocaleData> failed = localeStorageService.getAllFailedTransformations();
        
        assertTrue(failed.size() >= 1);
        assertTrue(failed.stream().allMatch(d -> !d.isSuccess()));
    }
    
    @Test
    public void testGetTransformationByLanguageAndCountry() {
        // Save transformation
        localeStorageService.saveSuccessfulTransformation("pt", "BR", "pt-BR", "id", "name");
        
        // Retrieve
        List<LocaleData> results = localeStorageService.getTransformationsByLanguageAndCountry("pt", "BR");
        
        assertTrue(results.size() >= 1);
        assertTrue(results.stream().anyMatch(d -> "pt-BR".equals(d.getTransformedLang())));
    }
}

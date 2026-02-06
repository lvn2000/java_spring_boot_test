package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity for storing transformed locale data
 */
@Entity
@Table(name = "locale_data")
public class LocaleData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String originalLang;
    
    @Column(nullable = false)
    private String originalCountry;
    
    @Column(nullable = true)
    private String transformedLang;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "api_response_id")
    private String apiResponseId;
    
    @Column(name = "api_response_name")
    private String apiResponseName;
    
    @Column(name = "error_message")
    private String errorMessage;
    
    @Column(nullable = false)
    private boolean success;

    public LocaleData() {
        this.createdAt = LocalDateTime.now();
    }

    public LocaleData(String originalLang, String originalCountry, String transformedLang, 
                     String apiResponseId, String apiResponseName) {
        this.originalLang = originalLang;
        this.originalCountry = originalCountry;
        this.transformedLang = transformedLang;
        this.apiResponseId = apiResponseId;
        this.apiResponseName = apiResponseName;
        this.success = true;
        this.createdAt = LocalDateTime.now();
    }

    public LocaleData(String originalLang, String originalCountry, String errorMessage) {
        this.originalLang = originalLang;
        this.originalCountry = originalCountry;
        this.errorMessage = errorMessage;
        this.success = false;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalLang() {
        return originalLang;
    }

    public void setOriginalLang(String originalLang) {
        this.originalLang = originalLang;
    }

    public String getOriginalCountry() {
        return originalCountry;
    }

    public void setOriginalCountry(String originalCountry) {
        this.originalCountry = originalCountry;
    }

    public String getTransformedLang() {
        return transformedLang;
    }

    public void setTransformedLang(String transformedLang) {
        this.transformedLang = transformedLang;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getApiResponseId() {
        return apiResponseId;
    }

    public void setApiResponseId(String apiResponseId) {
        this.apiResponseId = apiResponseId;
    }

    public String getApiResponseName() {
        return apiResponseName;
    }

    public void setApiResponseName(String apiResponseName) {
        this.apiResponseName = apiResponseName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "LocaleData{" +
                "id=" + id +
                ", originalLang='" + originalLang + '\'' +
                ", originalCountry='" + originalCountry + '\'' +
                ", transformedLang='" + transformedLang + '\'' +
                ", createdAt=" + createdAt +
                ", apiResponseId='" + apiResponseId + '\'' +
                ", success=" + success +
                '}';
    }
}

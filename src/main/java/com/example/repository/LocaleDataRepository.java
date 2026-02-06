package com.example.repository;

import com.example.model.LocaleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for LocaleData entity
 */
@Repository
public interface LocaleDataRepository extends JpaRepository<LocaleData, Long> {
    
    List<LocaleData> findByOriginalLangAndOriginalCountry(String originalLang, String originalCountry);
    
    List<LocaleData> findBySuccess(boolean success);
}
